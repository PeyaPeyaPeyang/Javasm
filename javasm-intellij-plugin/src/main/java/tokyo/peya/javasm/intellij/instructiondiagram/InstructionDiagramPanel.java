package tokyo.peya.javasm.intellij.instructiondiagram;

import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.ui.JBColor;
import com.intellij.util.ui.JBUI;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.dependency.*;
import tokyo.peya.javasm.intellij.editor.highlighting.JALSyntaxHighlighter;
import tokyo.peya.javasm.intellij.utils.JALMessages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.*;
import java.util.List;

public final class InstructionDiagramPanel extends JPanel {
    private static final int GRID_SIZE = 48;
    private static final float MIN_SCALE = 0.35f;
    private static final float MAX_SCALE = 2.5f;
    private static final float ZOOM_STEP = 1.1f;
    private static final float LANE_GAP = 96f;
    private static final float LEVEL_GAP = 86f;
    private static final float METHOD_SPACING = 96f;
    private static final float NODE_HEIGHT = 44f;
    private static final float NODE_MIN_WIDTH = 110f;
    private static final float NODE_PADDING_X = 18f;
    private static final float ARROW_SIZE = 8f;
    private static final float METHOD_BOX_PADDING_X = 24f;
    private static final float METHOD_BOX_PADDING_Y = 18f;
    private static final float METHOD_LABEL_HEIGHT = 30f;
    private static final float INSTRUCTION_SET_BOX_PADDING_X = 16f;
    private static final float INSTRUCTION_SET_BOX_PADDING_Y = 14f;
    private static final float INSTRUCTION_SET_LABEL_HEIGHT = 24f;
    private static final float INSTRUCTION_SET_MIN_MARGIN = 8f;
    private static final float SELF_JUMP_MARGIN = 28f;
    private static final float SELF_JUMP_SPACING = 14f;
    private static final float JUMP_OVERLAP_OFFSET = 10f;
    private final java.util.function.Consumer<InstructionDependencyEntry> onNavigate;
    private final List<MethodBox> methodBoxes;
    private final List<InstructionSetBox> instructionSetBoxes;
    private final List<DiagramNode> nodes;
    private final List<DiagramEdge> edges;
    private final List<DiagramJumpEdge> jumpEdges;

    private DiagramNode selectedNode;
    private Point dragStart;
    private InstructionDiagramSettings settings;
    private float scale;
    private float translateX;
    private float translateY;

    public InstructionDiagramPanel(@NotNull java.util.function.Consumer<InstructionDependencyEntry> onNavigate,
                                   @NotNull InstructionDiagramSettings settings) {
        this.onNavigate = onNavigate;
        this.settings = settings;
        this.methodBoxes = new ArrayList<>();
        this.instructionSetBoxes = new ArrayList<>();
        this.nodes = new ArrayList<>();
        this.edges = new ArrayList<>();
        this.jumpEdges = new ArrayList<>();
        this.scale = 1f;
        this.translateX = 120f;
        this.translateY = 120f;

        this.setOpaque(true);
        this.setBackground(this.settings.backgroundColor());
        this.setBorder(JBUI.Borders.customLine(JBColor.border(), 1));
        this.setToolTipText("");
        this.installInteractions();
    }

    public void applySettings(@NotNull InstructionDiagramSettings settings) {
        this.settings = settings;
        this.setBackground(settings.backgroundColor());
        this.repaint();
    }

    public void resetView() {
        this.scale = 1f;
        this.translateX = 120f;
        this.translateY = 120f;
        this.repaint();
    }

    public void clear() {
        this.methodBoxes.clear();
        this.instructionSetBoxes.clear();
        this.nodes.clear();
        this.edges.clear();
        this.jumpEdges.clear();
        this.selectedNode = null;
        this.repaint();
    }

    public void setAnalysisResult(@NotNull InstructionDependencyAnalysisResult result) {
        this.methodBoxes.clear();
        this.instructionSetBoxes.clear();
        this.nodes.clear();
        this.edges.clear();
        this.jumpEdges.clear();
        this.selectedNode = null;

        Map<InstructionDependencyEntry, DiagramNode> nodeMap = new HashMap<>();
        Map<InstructionSetDependencyGroup, InstructionSetBox> instructionSetBoxMap = new HashMap<>();
        FontMetrics metrics = this.getFontMetrics(this.getFont().deriveFont(Font.PLAIN, 18f));
        DiagramLayout layout = DiagramLayout.compute(result, metrics);
        this.methodBoxes.addAll(layout.methodBoxes());
        this.instructionSetBoxes.addAll(layout.instructionSetBoxes());
        this.nodes.addAll(layout.nodes());
        for (DiagramNode node : this.nodes)
            nodeMap.put(node.entry(), node);
        for (InstructionSetBox box : this.instructionSetBoxes)
            instructionSetBoxMap.put(box.group(), box);

        for (InstructionDependencyEdge edge : result.edges()) {
            DiagramNode from = nodeMap.get(edge.from());
            DiagramNode to = nodeMap.get(edge.to());
            if (from != null && to != null)
                this.edges.add(new DiagramEdge(from, to, edge.kind()));
        }
        for (InstructionSetJumpEdge jump : result.instructionSetJumps()) {
            DiagramNode from = nodeMap.get(jump.from());
            InstructionSetBox to = instructionSetBoxMap.get(jump.to());
            if (from != null && to != null)
                this.jumpEdges.add(new DiagramJumpEdge(from, to));
        }

        this.revalidate();
        this.repaint();
    }

    @Override
    public String getToolTipText(MouseEvent event) {
        DiagramNode node = this.findNode(event.getPoint());
        if (node == null)
            return null;
        InstructionDependencyEntry entry = node.entry();
        return entry.methodName() + entry.methodDescriptor() + " @" + entry.instructionOffset();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        if (this.settings.showGrid())
            this.paintGrid(g2);

        AffineTransform oldTransform = g2.getTransform();
        g2.translate(this.translateX, this.translateY);
        g2.scale(this.scale, this.scale);

        this.paintMethodBoxes(g2);
        this.paintInstructionSetBoxes(g2);
        this.paintEdges(g2);
        this.paintJumpEdges(g2);
        this.paintNodes(g2);

        g2.setTransform(oldTransform);
        if (this.settings.showHint())
            this.paintHint(g2);
        g2.dispose();
    }

    private void paintGrid(@NotNull Graphics2D g2) {
        g2.setColor(this.settings.gridColor());

        float scaledGrid = GRID_SIZE * this.scale;
        if (scaledGrid < 12f)
            scaledGrid = 12f;

        float startX = this.translateX % scaledGrid;
        float startY = this.translateY % scaledGrid;

        for (float x = startX; x < this.getWidth(); x += scaledGrid)
            g2.drawLine(Math.round(x), 0, Math.round(x), this.getHeight());
        for (float y = startY; y < this.getHeight(); y += scaledGrid)
            g2.drawLine(0, Math.round(y), this.getWidth(), Math.round(y));
    }

    private void paintMethodBoxes(@NotNull Graphics2D g2) {
        Font font = this.getFont().deriveFont(Font.PLAIN, 14f);
        g2.setFont(font);
        FontMetrics metrics = g2.getFontMetrics();

        for (MethodBox methodBox : this.methodBoxes) {
            Rectangle2D.Float bounds = methodBox.bounds();

            g2.setColor(this.settings.methodBoxFillColor());
            g2.fillRoundRect(
                    Math.round(bounds.x),
                    Math.round(bounds.y),
                    Math.round(bounds.width),
                    Math.round(bounds.height),
                    10,
                    10
            );

            g2.setColor(this.settings.methodBoxBorderColor());
            g2.setStroke(new BasicStroke(1.4f));
            g2.drawRoundRect(
                    Math.round(bounds.x),
                    Math.round(bounds.y),
                    Math.round(bounds.width),
                    Math.round(bounds.height),
                    10,
                    10
            );

            int labelX = Math.round(bounds.x + METHOD_BOX_PADDING_X);
            int labelY = Math.round(bounds.y + (METHOD_LABEL_HEIGHT + metrics.getAscent()) / 2f) - 3;
            g2.setColor(this.settings.methodBoxTextColor());
            g2.drawString(methodBox.label(), labelX, labelY);
        }
    }

    private void paintInstructionSetBoxes(@NotNull Graphics2D g2) {
        Font font = this.getFont().deriveFont(Font.PLAIN, 13f);
        g2.setFont(font);
        FontMetrics metrics = g2.getFontMetrics();

        for (InstructionSetBox instructionSetBox : this.instructionSetBoxes) {
            Rectangle2D.Float bounds = instructionSetBox.bounds();

            g2.setColor(this.settings.instructionSetFillColor());
            g2.fillRoundRect(
                    Math.round(bounds.x),
                    Math.round(bounds.y),
                    Math.round(bounds.width),
                    Math.round(bounds.height),
                    8,
                    8
            );

            g2.setColor(this.settings.instructionSetBorderColor());
            g2.setStroke(new BasicStroke(1.2f));
            g2.drawRoundRect(
                    Math.round(bounds.x),
                    Math.round(bounds.y),
                    Math.round(bounds.width),
                    Math.round(bounds.height),
                    8,
                    8
            );

            int labelX = Math.round(bounds.x + INSTRUCTION_SET_BOX_PADDING_X);
            int labelY = Math.round(bounds.y + (INSTRUCTION_SET_LABEL_HEIGHT + metrics.getAscent()) / 2f) - 3;
            g2.setColor(this.settings.instructionSetTextColor());
            g2.drawString(instructionSetBox.group().label(), labelX, labelY);
        }
    }

    private void paintEdges(@NotNull Graphics2D g2) {
        g2.setStroke(new BasicStroke(2f));

        List<DiagramEdge> orderedEdges = new ArrayList<>(this.edges);
        orderedEdges.sort(Comparator.comparing(DiagramEdge::kind).reversed());
        for (DiagramEdge edge : orderedEdges) {
            g2.setColor(this.edgeColor(edge.kind()));
            Rectangle2D.Float from = edge.from().bounds();
            Rectangle2D.Float to = edge.to().bounds();
            this.paintRoute(g2, this.routeOrthogonal(from, to));
        }
    }

    private @NotNull Color edgeColor(@NotNull InstructionDependencyEdgeKind kind) {
        return switch (kind) {
            case DATA -> this.settings.dataEdgeColor();
            case CONTROL -> this.settings.controlEdgeColor();
        };
    }

    private void paintJumpEdges(@NotNull Graphics2D g2) {
        g2.setStroke(new BasicStroke(2f));

        Map<JumpLaneKey, Map<InstructionSetBox, Integer>> laneByTarget = new HashMap<>();
        for (DiagramJumpEdge edge : this.jumpEdges) {
            Rectangle2D.Float from = edge.from().bounds();
            int lane = this.jumpLane(edge.to(), laneByTarget);
            OrthogonalRoute route = this.routeJumpFromBottom(from, edge.to(), lane);
            g2.setColor(this.jumpEdgeColor(lane));
            this.paintRoute(g2, route);
        }
    }

    private @NotNull Color jumpEdgeColor(int lane) {
        Color[] colors = this.settings.jumpEdgeColors();
        return colors[Math.floorMod(lane, colors.length)];
    }

    private int jumpLane(@NotNull InstructionSetBox toBox,
                         @NotNull Map<? super JumpLaneKey, Map<InstructionSetBox, Integer>> laneByTarget) {
        JumpLaneKey key = new JumpLaneKey(toBox.group().methodName(), toBox.group().methodDescriptor());
        Map<InstructionSetBox, Integer> lanes =
                laneByTarget.computeIfAbsent(key, ignored -> new HashMap<>());
        return lanes.computeIfAbsent(toBox, ignored -> lanes.size());
    }

    private @NotNull OrthogonalRoute routeJumpFromBottom(@NotNull Rectangle2D.Float from,
                                                         @NotNull InstructionSetBox toBox,
                                                         int lane) {
        Rectangle2D.Float to = toBox.bounds();
        List<InstructionSetBox> relatedBoxes = this.instructionSetBoxes.stream()
                .filter(box -> box.group().methodName().equals(toBox.group().methodName()))
                .filter(box -> box.group().methodDescriptor().equals(toBox.group().methodDescriptor()))
                .toList();

        float minX = to.x;
        float maxX = to.x + to.width;
        for (InstructionSetBox box : relatedBoxes) {
            Rectangle2D.Float bounds = box.bounds();
            minX = Math.min(minX, bounds.x);
            maxX = Math.max(maxX, bounds.x + bounds.width);
        }

        float fromCenterX = from.x + from.width / 2f;
        boolean routeRight = Math.abs(fromCenterX - maxX) <= Math.abs(fromCenterX - minX);
        float margin = SELF_JUMP_MARGIN + lane * JUMP_OVERLAP_OFFSET;
        float outsideX = routeRight ? maxX + margin : minX - margin;

        Point2D.Float start = ConnectionSide.BOTTOM.anchor(from);
        Point2D.Float end = routeRight ? ConnectionSide.RIGHT.anchor(to) : ConnectionSide.LEFT.anchor(to);
        float exitY = from.y + from.height + SELF_JUMP_MARGIN + lane * SELF_JUMP_SPACING;
        float targetY = end.y;

        return this.createRoute(List.of(
                start,
                new Point2D.Float(start.x, exitY),
                new Point2D.Float(outsideX, exitY),
                new Point2D.Float(outsideX, targetY),
                new Point2D.Float(end.x, targetY),
                end
        ));
    }

    private void paintRoute(@NotNull Graphics2D g2, @NotNull OrthogonalRoute route) {
        List<Point2D.Float> points = route.points();
        if (points.size() < 2)
            return;

        Path2D.Float path = new Path2D.Float();
        Point2D.Float first = points.getFirst();
        path.moveTo(first.x, first.y);
        for (int i = 1; i < points.size(); i++) {
            Point2D.Float point = points.get(i);
            path.lineTo(point.x, point.y);
        }
        g2.draw(path);

        Point2D.Float target = points.getLast();
        this.paintArrow(g2, target.x, target.y, route.arrowAngle());
    }

    private @NotNull OrthogonalRoute routeOrthogonal(@NotNull Rectangle2D.Float from,
                                                     @NotNull Rectangle2D.Float to) {
        OrthogonalRoute route = this.routeDirect(from, to);
        if (route != null)
            return route;

        route = this.routeOneBend(from, to);
        if (route != null)
            return route;

        return this.routeTwoBends(from, to);
    }

    private OrthogonalRoute routeDirect(@NotNull Rectangle2D.Float from, @NotNull Rectangle2D.Float to) {
        float overlapLeft = Math.max(from.x, to.x);
        float overlapRight = Math.min(from.x + from.width, to.x + to.width);
        if (overlapLeft <= overlapRight) {
            float x = (overlapLeft + overlapRight) / 2f;
            if (from.y + from.height <= to.y) {
                return this.createRoute(List.of(
                        new Point2D.Float(x, from.y + from.height),
                        new Point2D.Float(x, to.y)
                ));
            }
            if (to.y + to.height <= from.y) {
                return this.createRoute(List.of(
                        new Point2D.Float(x, from.y),
                        new Point2D.Float(x, to.y + to.height)
                ));
            }
        }

        float overlapTop = Math.max(from.y, to.y);
        float overlapBottom = Math.min(from.y + from.height, to.y + to.height);
        if (overlapTop <= overlapBottom) {
            float y = (overlapTop + overlapBottom) / 2f;
            if (from.x + from.width <= to.x) {
                return this.createRoute(List.of(
                        new Point2D.Float(from.x + from.width, y),
                        new Point2D.Float(to.x, y)
                ));
            }
            if (to.x + to.width <= from.x) {
                return this.createRoute(List.of(
                        new Point2D.Float(from.x, y),
                        new Point2D.Float(to.x + to.width, y)
                ));
            }
        }

        return null;
    }

    private OrthogonalRoute routeOneBend(@NotNull Rectangle2D.Float from, @NotNull Rectangle2D.Float to) {
        OrthogonalRoute best = null;
        for (ConnectionSide fromSide : ConnectionSide.values()) {
            for (ConnectionSide toSide : ConnectionSide.values()) {
                if (fromSide.isHorizontal() == toSide.isHorizontal())
                    continue;

                Point2D.Float start = fromSide.anchor(from);
                Point2D.Float end = toSide.anchor(to);
                Point2D.Float bend = fromSide.isHorizontal()
                        ? new Point2D.Float(end.x, start.y)
                        : new Point2D.Float(start.x, end.y);
                OrthogonalRoute route = this.createRoute(List.of(start, bend, end));
                if (!this.leavesSide(route.points().get(0), route.points().get(1), fromSide))
                    continue;
                if (!this.entersSide(route.points().get(route.points().size() - 2), route.points().getLast(), toSide))
                    continue;
                if (best == null || route.compareTo(best) < 0)
                    best = route;
            }
        }
        return best;
    }

    private @NotNull OrthogonalRoute routeTwoBends(@NotNull Rectangle2D.Float from, @NotNull Rectangle2D.Float to) {
        Point2D.Float start = ConnectionSide.BOTTOM.anchor(from);
        Point2D.Float end = ConnectionSide.TOP.anchor(to);
        if (to.y + to.height < from.y) {
            start = ConnectionSide.TOP.anchor(from);
            end = ConnectionSide.BOTTOM.anchor(to);
        }

        float midY = (start.y + end.y) / 2f;
        if (Math.abs(start.y - end.y) < 1f)
            midY = Math.min(from.y, to.y) - LEVEL_GAP / 2f;

        return this.createRoute(List.of(
                start,
                new Point2D.Float(start.x, midY),
                new Point2D.Float(end.x, midY),
                end
        ));
    }

    private boolean leavesSide(@NotNull Point2D.Float start,
                               @NotNull Point2D.Float next,
                               @NotNull ConnectionSide side) {
        return switch (side) {
            case TOP -> next.y <= start.y;
            case RIGHT -> next.x >= start.x;
            case BOTTOM -> next.y >= start.y;
            case LEFT -> next.x <= start.x;
        };
    }

    private boolean entersSide(@NotNull Point2D.Float previous,
                               @NotNull Point2D.Float end,
                               @NotNull ConnectionSide side) {
        return switch (side) {
            case TOP -> previous.y <= end.y;
            case RIGHT -> previous.x >= end.x;
            case BOTTOM -> previous.y >= end.y;
            case LEFT -> previous.x <= end.x;
        };
    }

    private @NotNull OrthogonalRoute createRoute(@NotNull List<Point2D.Float> rawPoints) {
        List<Point2D.Float> points = new ArrayList<>();
        for (Point2D.Float point : rawPoints) {
            if (points.isEmpty() || this.distanceSquared(points.get(points.size() - 1), point) > 0.5f)
                points.add(point);
        }

        int bends = 0;
        for (int i = 1; i < points.size() - 1; i++) {
            Point2D.Float previous = points.get(i - 1);
            Point2D.Float current = points.get(i);
            Point2D.Float next = points.get(i + 1);
            boolean firstHorizontal = Math.abs(previous.y - current.y) < 0.5f;
            boolean secondHorizontal = Math.abs(current.y - next.y) < 0.5f;
            if (firstHorizontal != secondHorizontal)
                bends++;
        }

        Point2D.Float beforeTarget = points.get(points.size() - 2);
        Point2D.Float target = points.getLast();
        double arrowAngle = Math.atan2(target.y - beforeTarget.y, target.x - beforeTarget.x);
        return new OrthogonalRoute(points, arrowAngle, bends, this.routeLength(points));
    }

    private float routeLength(@NotNull List<? extends Point2D.Float> points) {
        float length = 0f;
        for (int i = 1; i < points.size(); i++) {
            Point2D.Float previous = points.get(i - 1);
            Point2D.Float current = points.get(i);
            length += Math.abs(current.x - previous.x) + Math.abs(current.y - previous.y);
        }
        return length;
    }

    private float distanceSquared(@NotNull Point2D.Float from, @NotNull Point2D.Float to) {
        float dx = to.x - from.x;
        float dy = to.y - from.y;
        return dx * dx + dy * dy;
    }

    private void paintArrow(@NotNull Graphics2D g2, float x, float y, double angle) {
        double backX = Math.cos(angle) * ARROW_SIZE;
        double backY = Math.sin(angle) * ARROW_SIZE;
        double sideX = -Math.sin(angle) * ARROW_SIZE / 2d;
        double sideY = Math.cos(angle) * ARROW_SIZE / 2d;

        Path2D.Float arrow = new Path2D.Float();
        arrow.moveTo(x, y);
        arrow.lineTo(x - backX + sideX, y - backY + sideY);
        arrow.lineTo(x - backX - sideX, y - backY - sideY);
        arrow.closePath();
        g2.fill(arrow);
    }

    private void paintNodes(@NotNull Graphics2D g2) {
        Font font = this.getFont().deriveFont(Font.PLAIN, 18f);
        g2.setFont(font);
        FontMetrics metrics = g2.getFontMetrics();

        for (DiagramNode node : this.nodes) {
            Rectangle2D.Float bounds = node.bounds();
            boolean selected = node == this.selectedNode;
            Color fill = this.settings.nodeFillColor();
            Color border = this.borderColor(node.entry(), selected);
            Color text = this.settings.nodeTextColor();

            g2.setColor(fill);
            g2.fillRoundRect(Math.round(bounds.x), Math.round(bounds.y), Math.round(bounds.width), Math.round(bounds.height), 12, 12);

            g2.setColor(border);
            g2.setStroke(new BasicStroke(selected ? 2.2f : 1.2f));
            g2.drawRoundRect(Math.round(bounds.x), Math.round(bounds.y), Math.round(bounds.width), Math.round(bounds.height), 12, 12);

            String textValue = node.entry().instructionName();
            int textWidth = metrics.stringWidth(textValue);
            int textX = Math.round(bounds.x + (bounds.width - textWidth) / 2f);
            int textY = Math.round(bounds.y + (bounds.height + metrics.getAscent()) / 2f) - 4;
            g2.setColor(text);
            g2.drawString(textValue, textX, textY);
        }
    }

    private Color instructionHighlightColor(@NotNull InstructionDependencyEntry entry) {
        TextAttributesKey key = JALSyntaxHighlighter.getInstructionHighlightKey(entry.instructionName());
        if (key == null)
            return null;

        TextAttributes attributes = EditorColorsManager.getInstance().getGlobalScheme().getAttributes(key);
        if (attributes != null && attributes.getForegroundColor() != null)
            return attributes.getForegroundColor();

        TextAttributes defaultAttributes = key.getDefaultAttributes();
        if (defaultAttributes.getForegroundColor() != null)
            return defaultAttributes.getForegroundColor();

        return null;
    }

    private @NotNull Color borderColor(@NotNull InstructionDependencyEntry entry, boolean selected) {
        Color highlightColor = this.instructionHighlightColor(entry);
        if (highlightColor != null)
            return this.selectionColor(highlightColor, selected);

        return switch (entry.kind()) {
            case NEUTRAL -> this.selectionColor(this.settings.neutralNodeBorderColor(), selected);
            case PRODUCER -> this.selectionColor(this.settings.producerNodeBorderColor(), selected);
            case BOTH -> this.selectionColor(this.settings.bothNodeBorderColor(), selected);
            case CONSUMER -> this.selectionColor(this.settings.consumerNodeBorderColor(), selected);
        };
    }

    private void paintHint(@NotNull Graphics2D g2) {
        g2.setColor(this.settings.nodeTextColor());
        g2.setFont(this.getFont().deriveFont(Font.PLAIN, 12f));
        g2.drawString(JALMessages.message("jal.instructionDiagram.diagram.hint"), 14, this.getHeight() - 14);
    }

    private @NotNull Color selectionColor(@NotNull Color baseColor, boolean selected) {
        return selected ? baseColor.brighter() : baseColor;
    }

    private void installInteractions() {
        MouseAdapter adapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                InstructionDiagramPanel.this.dragStart = e.getPoint();
                InstructionDiagramPanel.this.selectedNode = findNode(e.getPoint());
                repaint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (!InstructionDiagramPanel.this.settings.enablePan())
                    return;
                if (InstructionDiagramPanel.this.dragStart == null)
                    return;
                Point current = e.getPoint();
                InstructionDiagramPanel.this.translateX += current.x - InstructionDiagramPanel.this.dragStart.x;
                InstructionDiagramPanel.this.translateY += current.y - InstructionDiagramPanel.this.dragStart.y;
                InstructionDiagramPanel.this.dragStart = current;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                InstructionDiagramPanel.this.dragStart = null;
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (!InstructionDiagramPanel.this.settings.enableNodeNavigation())
                    return;
                if (e.getClickCount() != 2)
                    return;
                DiagramNode node = findNode(e.getPoint());
                if (node != null)
                    InstructionDiagramPanel.this.onNavigate.accept(node.entry());
            }

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if (!InstructionDiagramPanel.this.settings.enableZoom())
                    return;
                float oldScale = InstructionDiagramPanel.this.scale;
                float newScale = e.getWheelRotation() < 0 ? InstructionDiagramPanel.this.scale * ZOOM_STEP : InstructionDiagramPanel.this.scale / ZOOM_STEP;
                InstructionDiagramPanel.this.scale = Math.max(MIN_SCALE, Math.min(MAX_SCALE, newScale));
                if (oldScale == InstructionDiagramPanel.this.scale)
                    return;

                Point2D.Float worldBefore = toWorld(e.getPoint(), oldScale);
                InstructionDiagramPanel.this.translateX = e.getPoint().x - worldBefore.x * InstructionDiagramPanel.this.scale;
                InstructionDiagramPanel.this.translateY = e.getPoint().y - worldBefore.y * InstructionDiagramPanel.this.scale;
                repaint();
            }
        };

        this.addMouseListener(adapter);
        this.addMouseMotionListener(adapter);
        this.addMouseWheelListener(adapter);
    }

    private DiagramNode findNode(@NotNull Point point) {
        Point2D.Float world = this.toWorld(point, this.scale);
        for (DiagramNode node : this.nodes) {
            if (node.bounds().contains(world))
                return node;
        }
        return null;
    }

    private @NotNull Point2D.Float toWorld(@NotNull Point point, float activeScale) {
        return new Point2D.Float(
                (point.x - this.translateX) / activeScale,
                (point.y - this.translateY) / activeScale
        );
    }

    private enum ConnectionSide {
        TOP,
        RIGHT,
        BOTTOM,
        LEFT;

        private boolean isHorizontal() {
            return this == LEFT || this == RIGHT;
        }

        private @NotNull Point2D.Float anchor(@NotNull Rectangle2D.Float rect) {
            return switch (this) {
                case TOP -> new Point2D.Float(rect.x + rect.width / 2f, rect.y);
                case RIGHT -> new Point2D.Float(rect.x + rect.width, rect.y + rect.height / 2f);
                case BOTTOM -> new Point2D.Float(rect.x + rect.width / 2f, rect.y + rect.height);
                case LEFT -> new Point2D.Float(rect.x, rect.y + rect.height / 2f);
            };
        }
    }

    private record DiagramLayout(@NotNull List<DiagramNode> nodes,
                                 @NotNull List<InstructionSetBox> instructionSetBoxes,
                                 @NotNull List<MethodBox> methodBoxes) {
        private static @NotNull DiagramLayout compute(@NotNull InstructionDependencyAnalysisResult result,
                                                      @NotNull FontMetrics metrics) {
            Map<MethodKey, List<InstructionDependencyEntry>> methodEntries = groupEntriesByMethod(result);
            Map<InstructionDependencyEntry, List<InstructionDependencyEdge>> incomingEdges = groupIncomingEdges(result);
            List<Map.Entry<MethodKey, List<InstructionDependencyEntry>>> methods = new ArrayList<>(methodEntries.entrySet());
            methods.sort(Map.Entry.comparingByKey());

            List<DiagramNode> nodes = new ArrayList<>();
            List<PlacedEntry> placedEntries = new ArrayList<>();
            Map<InstructionDependencyEntry, NodePlacement> placements = new HashMap<>();
            Map<Integer, Float> widthByLane = new HashMap<>();
            float methodY = 0f;

            for (Map.Entry<MethodKey, List<InstructionDependencyEntry>> methodEntry : methods) {
                MethodKey methodKey = methodEntry.getKey();
                List<InstructionDependencyEntry> ordered = new ArrayList<>(methodEntry.getValue());
                ordered.sort(Comparator.comparingInt(InstructionDependencyEntry::instructionOffset));

                MethodLayoutState state = new MethodLayoutState();
                for (InstructionDependencyEntry entry : ordered) {
                    NodePlacement placement = state.place(entry, incomingEdges.getOrDefault(entry, List.of()), placements);
                    placements.put(entry, placement);

                    float width = Math.max(NODE_MIN_WIDTH, metrics.stringWidth(entry.instructionName()) + NODE_PADDING_X * 2);
                    PlacedEntry placedEntry = new PlacedEntry(entry, methodKey, placement, width, methodY + placement.level() * LEVEL_GAP);
                    placedEntries.add(placedEntry);
                    widthByLane.merge(placement.lane(), width, Math::max);
                }

                methodY += state.height() + METHOD_SPACING;
            }

            Map<Integer, Float> xByLane = computeXByLane(widthByLane);
            Map<MethodKey, List<DiagramNode>> nodesByMethod = new HashMap<>();
            Map<InstructionDependencyEntry, DiagramNode> nodeByEntry = new HashMap<>();
            Map<InstructionKey, DiagramNode> nodeByInstruction = new HashMap<>();
            for (PlacedEntry placedEntry : placedEntries) {
                Rectangle2D.Float bounds = new Rectangle2D.Float(
                        xByLane.getOrDefault(placedEntry.placement().lane(), 0f),
                        placedEntry.y(),
                        placedEntry.width(),
                        NODE_HEIGHT
                );
                DiagramNode node = new DiagramNode(placedEntry.entry(), bounds);
                nodes.add(node);
                nodeByEntry.put(placedEntry.entry(), node);
                nodeByInstruction.put(new InstructionKey(
                        placedEntry.entry().methodName(),
                        placedEntry.entry().methodDescriptor(),
                        placedEntry.entry().instructionOffset()
                ), node);
                nodesByMethod.computeIfAbsent(placedEntry.methodKey(), ignored -> new ArrayList<>()).add(node);
            }

            List<InstructionSetBox> instructionSetBoxes = createInstructionSetBoxes(result, nodeByInstruction);
            Map<MethodKey, List<InstructionSetBox>> instructionSetBoxesByMethod = new HashMap<>();
            for (InstructionSetBox box : instructionSetBoxes) {
                InstructionSetDependencyGroup group = box.group();
                MethodKey methodKey = new MethodKey(group.methodName(), group.methodDescriptor());
                instructionSetBoxesByMethod.computeIfAbsent(methodKey, ignored -> new ArrayList<>()).add(box);
            }
            resolveInstructionSetOverlaps(methods, nodesByMethod, instructionSetBoxesByMethod, nodeByInstruction);

            List<MethodBox> methodBoxes = new ArrayList<>();
            for (Map.Entry<MethodKey, List<DiagramNode>> methodEntry : nodesByMethod.entrySet()) {
                MethodBox methodBox = createMethodBox(
                        methodEntry.getKey(),
                        methodEntry.getValue(),
                        instructionSetBoxesByMethod.getOrDefault(methodEntry.getKey(), List.of())
                );
                if (methodBox != null)
                    methodBoxes.add(methodBox);
            }
            methodBoxes.sort(Comparator.comparing(MethodBox::label));

            return new DiagramLayout(nodes, instructionSetBoxes, methodBoxes);
        }

        private static MethodBox createMethodBox(@NotNull MethodKey methodKey,
                                                 @NotNull List<DiagramNode> nodes,
                                                 @NotNull List<InstructionSetBox> instructionSetBoxes) {
            if (nodes.isEmpty() && instructionSetBoxes.isEmpty())
                return null;

            float minX = Float.MAX_VALUE;
            float minY = Float.MAX_VALUE;
            float maxX = -Float.MAX_VALUE;
            float maxY = -Float.MAX_VALUE;
            for (DiagramNode node : nodes) {
                Rectangle2D.Float bounds = node.bounds();
                minX = Math.min(minX, bounds.x);
                minY = Math.min(minY, bounds.y);
                maxX = Math.max(maxX, bounds.x + bounds.width);
                maxY = Math.max(maxY, bounds.y + bounds.height);
            }
            for (InstructionSetBox instructionSetBox : instructionSetBoxes) {
                Rectangle2D.Float bounds = instructionSetBox.bounds();
                minX = Math.min(minX, bounds.x);
                minY = Math.min(minY, bounds.y);
                maxX = Math.max(maxX, bounds.x + bounds.width);
                maxY = Math.max(maxY, bounds.y + bounds.height);
            }

            Rectangle2D.Float bounds = new Rectangle2D.Float(
                    minX - METHOD_BOX_PADDING_X,
                    minY - METHOD_LABEL_HEIGHT - METHOD_BOX_PADDING_Y,
                    maxX - minX + METHOD_BOX_PADDING_X * 2,
                    maxY - minY + METHOD_LABEL_HEIGHT + METHOD_BOX_PADDING_Y * 2
            );
            return new MethodBox(methodKey.displayName(), bounds);
        }

        private static @NotNull List<InstructionSetBox> createInstructionSetBoxes(
                @NotNull InstructionDependencyAnalysisResult result,
                @NotNull Map<InstructionKey, DiagramNode> nodeByInstruction
        ) {
            List<InstructionSetBox> instructionSetBoxes = new ArrayList<>();
            for (InstructionSetDependencyGroup group : result.instructionSets()) {
                List<DiagramNode> groupNodes = new ArrayList<>();
                for (Integer offset : group.instructionOffsets()) {
                    DiagramNode node = nodeByInstruction.get(new InstructionKey(
                            group.methodName(),
                            group.methodDescriptor(),
                            offset
                    ));
                    if (node != null)
                        groupNodes.add(node);
                }

                InstructionSetBox box = createInstructionSetBox(group, groupNodes);
                if (box != null)
                    instructionSetBoxes.add(box);
            }

            instructionSetBoxes.sort(Comparator.comparing((InstructionSetBox box) -> box.group().methodName())
                    .thenComparing(box -> box.group().methodDescriptor())
                    .thenComparingInt(box -> box.group().index()));
            return instructionSetBoxes;
        }

        private static InstructionSetBox createInstructionSetBox(@NotNull InstructionSetDependencyGroup group,
                                                                 @NotNull List<DiagramNode> nodes) {
            if (nodes.isEmpty())
                return null;

            float minX = Float.MAX_VALUE;
            float minY = Float.MAX_VALUE;
            float maxX = -Float.MAX_VALUE;
            float maxY = -Float.MAX_VALUE;
            for (DiagramNode node : nodes) {
                Rectangle2D.Float bounds = node.bounds();
                minX = Math.min(minX, bounds.x);
                minY = Math.min(minY, bounds.y);
                maxX = Math.max(maxX, bounds.x + bounds.width);
                maxY = Math.max(maxY, bounds.y + bounds.height);
            }

            Rectangle2D.Float bounds = new Rectangle2D.Float(
                    minX - INSTRUCTION_SET_BOX_PADDING_X,
                    minY - INSTRUCTION_SET_LABEL_HEIGHT - INSTRUCTION_SET_BOX_PADDING_Y,
                    maxX - minX + INSTRUCTION_SET_BOX_PADDING_X * 2,
                    maxY - minY + INSTRUCTION_SET_LABEL_HEIGHT + INSTRUCTION_SET_BOX_PADDING_Y * 2
            );
            return new InstructionSetBox(group, bounds);
        }

        private static void resolveInstructionSetOverlaps(
                @NotNull List<? extends Map.Entry<MethodKey, List<InstructionDependencyEntry>>> orderedMethods,
                @NotNull Map<MethodKey, List<DiagramNode>> nodesByMethod,
                @NotNull Map<MethodKey, List<InstructionSetBox>> instructionSetBoxesByMethod,
                @NotNull Map<InstructionKey, DiagramNode> nodeByInstruction
        ) {
            float requiredMethodTop = Float.NEGATIVE_INFINITY;
            for (Map.Entry<MethodKey, List<InstructionDependencyEntry>> methodEntry : orderedMethods) {
                MethodKey methodKey = methodEntry.getKey();
                List<DiagramNode> methodNodes = nodesByMethod.getOrDefault(methodKey, List.of());
                List<InstructionSetBox> boxes =
                        new ArrayList<>(instructionSetBoxesByMethod.getOrDefault(methodKey, List.of()));
                if (methodNodes.isEmpty() && boxes.isEmpty())
                    continue;

                float methodTop = top(methodNodes, boxes);
                if (methodTop < requiredMethodTop)
                    shiftMethod(methodNodes, boxes, requiredMethodTop - methodTop);

                boxes.sort(Comparator.comparingInt(box -> box.group().index()));
                float requiredBoxTop = Float.NEGATIVE_INFINITY;
                for (InstructionSetBox box : boxes) {
                    if (box.bounds().y < requiredBoxTop) {
                        float delta = requiredBoxTop - box.bounds().y;
                        shiftInstructionSet(box, nodeByInstruction, delta);
                    }
                    requiredBoxTop = box.bounds().y + box.bounds().height + INSTRUCTION_SET_MIN_MARGIN;
                }

                requiredMethodTop = bottom(methodNodes, boxes) + METHOD_SPACING;
            }
        }

        private static void shiftMethod(@NotNull List<DiagramNode> nodes,
                                        @NotNull List<InstructionSetBox> boxes,
                                        float delta) {
            for (DiagramNode node : nodes)
                node.bounds().y += delta;
            for (InstructionSetBox box : boxes)
                box.bounds().y += delta;
        }

        private static void shiftInstructionSet(@NotNull InstructionSetBox box,
                                                @NotNull Map<InstructionKey, DiagramNode> nodeByInstruction,
                                                float delta) {
            InstructionSetDependencyGroup group = box.group();
            for (Integer offset : group.instructionOffsets()) {
                DiagramNode node = nodeByInstruction.get(new InstructionKey(
                        group.methodName(),
                        group.methodDescriptor(),
                        offset
                ));
                if (node != null)
                    node.bounds().y += delta;
            }
            box.bounds().y += delta;
        }

        private static float top(@NotNull List<DiagramNode> nodes, @NotNull List<InstructionSetBox> boxes) {
            float top = Float.MAX_VALUE;
            for (DiagramNode node : nodes)
                top = Math.min(top, node.bounds().y);
            for (InstructionSetBox box : boxes)
                top = Math.min(top, box.bounds().y);
            return top;
        }

        private static float bottom(@NotNull List<DiagramNode> nodes, @NotNull List<InstructionSetBox> boxes) {
            float bottom = -Float.MAX_VALUE;
            for (DiagramNode node : nodes)
                bottom = Math.max(bottom, node.bounds().y + node.bounds().height);
            for (InstructionSetBox box : boxes)
                bottom = Math.max(bottom, box.bounds().y + box.bounds().height);
            return bottom;
        }

        private static @NotNull Map<Integer, Float> computeXByLane(@NotNull Map<Integer, Float> widthByLane) {
            Map<Integer, Float> xByLane = new HashMap<>();
            int maxLane = widthByLane.keySet().stream().max(Integer::compareTo).orElse(0);
            float x = 0f;
            for (int lane = 0; lane <= maxLane; lane++) {
                xByLane.put(lane, x);
                x += widthByLane.getOrDefault(lane, NODE_MIN_WIDTH) + LANE_GAP;
            }
            return xByLane;
        }

        private static @NotNull Map<MethodKey, List<InstructionDependencyEntry>> groupEntriesByMethod(
                @NotNull InstructionDependencyAnalysisResult result
        ) {
            List<InstructionDependencyEntry> allEntries = new ArrayList<>(result.getAllEntries());
            for (InstructionDependencyEdge edge : result.edges()) {
                if (!allEntries.contains(edge.from()))
                    allEntries.add(edge.from());
                if (!allEntries.contains(edge.to()))
                    allEntries.add(edge.to());
            }

            allEntries.sort(Comparator.comparing(InstructionDependencyEntry::methodName)
                    .thenComparing(InstructionDependencyEntry::methodDescriptor)
                    .thenComparingInt(InstructionDependencyEntry::instructionOffset));

            Map<MethodKey, List<InstructionDependencyEntry>> methodEntries = new HashMap<>();
            for (InstructionDependencyEntry entry : allEntries) {
                MethodKey key = new MethodKey(entry.methodName(), entry.methodDescriptor());
                methodEntries.computeIfAbsent(key, ignored -> new ArrayList<>()).add(entry);
            }
            return methodEntries;
        }

        private static @NotNull Map<InstructionDependencyEntry, List<InstructionDependencyEdge>> groupIncomingEdges(
                @NotNull InstructionDependencyAnalysisResult result
        ) {
            Map<InstructionDependencyEntry, List<InstructionDependencyEdge>> incomingEdges = new HashMap<>();
            for (InstructionDependencyEdge edge : result.edges()) {
                if (edge.kind() != InstructionDependencyEdgeKind.DATA)
                    continue;
                incomingEdges.computeIfAbsent(edge.to(), ignored -> new ArrayList<>()).add(edge);
            }
            return incomingEdges;
        }
    }

    private static final class MethodLayoutState {
        private final Set<Integer> activeLanes;
        private final Map<Integer, Integer> lastLevelByLane;
        private int lastLevel;

        private MethodLayoutState() {
            this.activeLanes = new HashSet<>();
            this.lastLevelByLane = new HashMap<>();
            this.lastLevel = -1;
        }

        private @NotNull NodePlacement place(@NotNull InstructionDependencyEntry entry,
                                             @NotNull List<InstructionDependencyEdge> incomingEdges,
                                             @NotNull Map<InstructionDependencyEntry, NodePlacement> placements) {
            List<NodePlacement> incomingPlacements = new ArrayList<>();
            for (InstructionDependencyEdge edge : incomingEdges) {
                NodePlacement placement = placements.get(edge.from());
                if (placement != null)
                    incomingPlacements.add(placement);
            }

            incomingPlacements.sort(Comparator.comparingInt(NodePlacement::lane));
            int lane = incomingPlacements.isEmpty()
                    ? this.firstFreeLane()
                    : incomingPlacements.get((incomingPlacements.size() - 1) / 2).lane();
            int dependencyLevel = 0;
            for (NodePlacement incomingPlacement : incomingPlacements)
                dependencyLevel = Math.max(dependencyLevel, incomingPlacement.level() + 1);

            int level = Math.max(dependencyLevel, this.lastLevel + 1);
            level = Math.max(level, this.lastLevelByLane.getOrDefault(lane, -1) + 1);
            this.lastLevelByLane.put(lane, level);
            this.lastLevel = Math.max(this.lastLevel, level);

            for (NodePlacement incomingPlacement : incomingPlacements)
                this.activeLanes.remove(incomingPlacement.lane());
            if (entry.producedCount() > 0)
                this.activeLanes.add(lane);
            else
                this.activeLanes.remove(lane);

            return new NodePlacement(level, lane);
        }

        private int firstFreeLane() {
            int lane = 0;
            while (this.activeLanes.contains(lane))
                lane++;
            return lane;
        }

        private float height() {
            return Math.max(0, this.lastLevel) * LEVEL_GAP + NODE_HEIGHT;
        }
    }

    private record MethodKey(@NotNull String name, @NotNull String descriptor) implements Comparable<MethodKey> {
        @Override
        public int compareTo(@NotNull MethodKey other) {
            int nameComparison = this.name.compareTo(other.name);
            if (nameComparison != 0)
                return nameComparison;
            return this.descriptor.compareTo(other.descriptor);
        }

        private @NotNull String displayName() {
            return this.name + this.descriptor;
        }
    }

    private record NodePlacement(int level, int lane) {
    }

    private record InstructionKey(@NotNull String methodName,
                                  @NotNull String methodDescriptor,
                                  int instructionOffset) {
    }

    private record PlacedEntry(@NotNull InstructionDependencyEntry entry,
                               @NotNull MethodKey methodKey,
                               @NotNull NodePlacement placement,
                               float width,
                               float y) {
    }

    private record MethodBox(@NotNull String label, @NotNull Rectangle2D.Float bounds) {
    }

    private record InstructionSetBox(@NotNull InstructionSetDependencyGroup group,
                                     @NotNull Rectangle2D.Float bounds) {
    }

    private record DiagramNode(@NotNull InstructionDependencyEntry entry, @NotNull Rectangle2D.Float bounds) {
    }

    private record DiagramEdge(@NotNull DiagramNode from,
                               @NotNull DiagramNode to,
                               @NotNull InstructionDependencyEdgeKind kind) {
    }

    private record DiagramJumpEdge(@NotNull DiagramNode from, @NotNull InstructionSetBox to) {
    }

    private record JumpLaneKey(@NotNull String methodName, @NotNull String methodDescriptor) {
    }

    private record OrthogonalRoute(@NotNull List<Point2D.Float> points,
                                   double arrowAngle,
                                   int bends,
                                   float length) implements Comparable<OrthogonalRoute> {
        @Override
        public int compareTo(@NotNull OrthogonalRoute other) {
            int bendComparison = Integer.compare(this.bends, other.bends);
            if (bendComparison != 0)
                return bendComparison;
            return Float.compare(this.length, other.length);
        }
    }
}
