package tokyo.peya.javasm.intellij.instructiondiagram;

import com.intellij.ui.JBColor;
import com.intellij.ui.components.JBCheckBox;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.util.ui.FormBuilder;
import com.intellij.util.ui.JBUI;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.utils.JALMessages;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public final class InstructionDiagramSettingsPanel {
    private final JPanel mainPanel;
    private final Consumer<InstructionDiagramSettings> onSettingsChanged;
    private final JBCheckBox showGridCheckBox;
    private final JBCheckBox showHintCheckBox;
    private final JBCheckBox enablePanCheckBox;
    private final JBCheckBox enableZoomCheckBox;
    private final JBCheckBox enableNodeNavigationCheckBox;
    private final JBCheckBox autoRefreshEnabledCheckBox;
    private final JSpinner autoRefreshDelaySpinner;
    private final JBCheckBox resetViewOnRefreshCheckBox;
    private final ColorButton backgroundColorButton;
    private final ColorButton gridColorButton;
    private final ColorButton methodBoxFillColorButton;
    private final ColorButton methodBoxBorderColorButton;
    private final ColorButton methodBoxTextColorButton;
    private final ColorButton instructionSetFillColorButton;
    private final ColorButton instructionSetBorderColorButton;
    private final ColorButton instructionSetTextColorButton;
    private final ColorButton nodeFillColorButton;
    private final ColorButton nodeTextColorButton;
    private final ColorButton dataEdgeColorButton;
    private final ColorButton controlEdgeColorButton;
    private final ColorButton neutralNodeBorderColorButton;
    private final ColorButton producerNodeBorderColorButton;
    private final ColorButton bothNodeBorderColorButton;
    private final ColorButton consumerNodeBorderColorButton;
    private final ColorButton jumpEdgeColor1Button;
    private final ColorButton jumpEdgeColor2Button;
    private final ColorButton jumpEdgeColor3Button;
    private final ColorButton jumpEdgeColor4Button;
    private boolean updating;

    public InstructionDiagramSettingsPanel(@NotNull InstructionDiagramSettings settings,
                                           @NotNull Consumer<InstructionDiagramSettings> onSettingsChanged) {
        this.onSettingsChanged = onSettingsChanged;
        this.showGridCheckBox = new JBCheckBox();
        this.showHintCheckBox = new JBCheckBox();
        this.enablePanCheckBox = new JBCheckBox();
        this.enableZoomCheckBox = new JBCheckBox();
        this.enableNodeNavigationCheckBox = new JBCheckBox();
        this.autoRefreshEnabledCheckBox = new JBCheckBox();
        this.autoRefreshDelaySpinner = new JSpinner(new SpinnerNumberModel(2500, 250, 60000, 250));
        this.resetViewOnRefreshCheckBox = new JBCheckBox();
        this.backgroundColorButton = new ColorButton();
        this.gridColorButton = new ColorButton();
        this.methodBoxFillColorButton = new ColorButton();
        this.methodBoxBorderColorButton = new ColorButton();
        this.methodBoxTextColorButton = new ColorButton();
        this.instructionSetFillColorButton = new ColorButton();
        this.instructionSetBorderColorButton = new ColorButton();
        this.instructionSetTextColorButton = new ColorButton();
        this.nodeFillColorButton = new ColorButton();
        this.nodeTextColorButton = new ColorButton();
        this.dataEdgeColorButton = new ColorButton();
        this.controlEdgeColorButton = new ColorButton();
        this.neutralNodeBorderColorButton = new ColorButton();
        this.producerNodeBorderColorButton = new ColorButton();
        this.bothNodeBorderColorButton = new ColorButton();
        this.consumerNodeBorderColorButton = new ColorButton();
        this.jumpEdgeColor1Button = new ColorButton();
        this.jumpEdgeColor2Button = new ColorButton();
        this.jumpEdgeColor3Button = new ColorButton();
        this.jumpEdgeColor4Button = new ColorButton();
        this.mainPanel = this.createMainPanel();
        this.installListeners();
        this.setSettings(settings);
    }

    public @NotNull JComponent getComponent() {
        return this.mainPanel;
    }

    public void setSettings(@NotNull InstructionDiagramSettings settings) {
        this.updating = true;
        try {
            this.showGridCheckBox.setSelected(settings.showGrid());
            this.showHintCheckBox.setSelected(settings.showHint());
            this.enablePanCheckBox.setSelected(settings.enablePan());
            this.enableZoomCheckBox.setSelected(settings.enableZoom());
            this.enableNodeNavigationCheckBox.setSelected(settings.enableNodeNavigation());
            this.autoRefreshEnabledCheckBox.setSelected(settings.autoRefreshEnabled());
            this.autoRefreshDelaySpinner.setValue(settings.autoRefreshDelayMs());
            this.resetViewOnRefreshCheckBox.setSelected(settings.resetViewOnRefresh());
            this.backgroundColorButton.setColor(settings.backgroundColor());
            this.gridColorButton.setColor(settings.gridColor());
            this.methodBoxFillColorButton.setColor(settings.methodBoxFillColor());
            this.methodBoxBorderColorButton.setColor(settings.methodBoxBorderColor());
            this.methodBoxTextColorButton.setColor(settings.methodBoxTextColor());
            this.instructionSetFillColorButton.setColor(settings.instructionSetFillColor());
            this.instructionSetBorderColorButton.setColor(settings.instructionSetBorderColor());
            this.instructionSetTextColorButton.setColor(settings.instructionSetTextColor());
            this.nodeFillColorButton.setColor(settings.nodeFillColor());
            this.nodeTextColorButton.setColor(settings.nodeTextColor());
            this.dataEdgeColorButton.setColor(settings.dataEdgeColor());
            this.controlEdgeColorButton.setColor(settings.controlEdgeColor());
            this.neutralNodeBorderColorButton.setColor(settings.neutralNodeBorderColor());
            this.producerNodeBorderColorButton.setColor(settings.producerNodeBorderColor());
            this.bothNodeBorderColorButton.setColor(settings.bothNodeBorderColor());
            this.consumerNodeBorderColorButton.setColor(settings.consumerNodeBorderColor());
            this.jumpEdgeColor1Button.setColor(settings.jumpEdgeColor1());
            this.jumpEdgeColor2Button.setColor(settings.jumpEdgeColor2());
            this.jumpEdgeColor3Button.setColor(settings.jumpEdgeColor3());
            this.jumpEdgeColor4Button.setColor(settings.jumpEdgeColor4());
        } finally {
            this.updating = false;
        }
    }

    private @NotNull JPanel createMainPanel() {
        JPanel visualPanel = FormBuilder.createFormBuilder()
                .addLabeledComponent(JALMessages.message("jal.instructionDiagram.settings.backgroundColor"), this.backgroundColorButton)
                .addLabeledComponent(JALMessages.message("jal.instructionDiagram.settings.gridColor"), this.gridColorButton)
                .addLabeledComponent(JALMessages.message("jal.instructionDiagram.settings.methodBoxFillColor"), this.methodBoxFillColorButton)
                .addLabeledComponent(JALMessages.message("jal.instructionDiagram.settings.methodBoxBorderColor"), this.methodBoxBorderColorButton)
                .addLabeledComponent(JALMessages.message("jal.instructionDiagram.settings.methodBoxTextColor"), this.methodBoxTextColorButton)
                .addLabeledComponent(JALMessages.message("jal.instructionDiagram.settings.instructionSetFillColor"), this.instructionSetFillColorButton)
                .addLabeledComponent(JALMessages.message("jal.instructionDiagram.settings.instructionSetBorderColor"), this.instructionSetBorderColorButton)
                .addLabeledComponent(JALMessages.message("jal.instructionDiagram.settings.instructionSetTextColor"), this.instructionSetTextColorButton)
                .addLabeledComponent(JALMessages.message("jal.instructionDiagram.settings.nodeFillColor"), this.nodeFillColorButton)
                .addLabeledComponent(JALMessages.message("jal.instructionDiagram.settings.nodeTextColor"), this.nodeTextColorButton)
                .addLabeledComponent(JALMessages.message("jal.instructionDiagram.settings.dataEdgeColor"), this.dataEdgeColorButton)
                .addLabeledComponent(JALMessages.message("jal.instructionDiagram.settings.controlEdgeColor"), this.controlEdgeColorButton)
                .addLabeledComponent(JALMessages.message("jal.instructionDiagram.settings.neutralNodeBorderColor"), this.neutralNodeBorderColorButton)
                .addLabeledComponent(JALMessages.message("jal.instructionDiagram.settings.producerNodeBorderColor"), this.producerNodeBorderColorButton)
                .addLabeledComponent(JALMessages.message("jal.instructionDiagram.settings.bothNodeBorderColor"), this.bothNodeBorderColorButton)
                .addLabeledComponent(JALMessages.message("jal.instructionDiagram.settings.consumerNodeBorderColor"), this.consumerNodeBorderColorButton)
                .addLabeledComponent(JALMessages.message("jal.instructionDiagram.settings.jumpEdgeColor1"), this.jumpEdgeColor1Button)
                .addLabeledComponent(JALMessages.message("jal.instructionDiagram.settings.jumpEdgeColor2"), this.jumpEdgeColor2Button)
                .addLabeledComponent(JALMessages.message("jal.instructionDiagram.settings.jumpEdgeColor3"), this.jumpEdgeColor3Button)
                .addLabeledComponent(JALMessages.message("jal.instructionDiagram.settings.jumpEdgeColor4"), this.jumpEdgeColor4Button)
                .getPanel();

        JPanel behaviorPanel = FormBuilder.createFormBuilder()
                .addLabeledComponent(JALMessages.message("jal.instructionDiagram.settings.showGrid"), this.showGridCheckBox)
                .addLabeledComponent(JALMessages.message("jal.instructionDiagram.settings.showHint"), this.showHintCheckBox)
                .addLabeledComponent(JALMessages.message("jal.instructionDiagram.settings.enablePan"), this.enablePanCheckBox)
                .addLabeledComponent(JALMessages.message("jal.instructionDiagram.settings.enableZoom"), this.enableZoomCheckBox)
                .addLabeledComponent(JALMessages.message("jal.instructionDiagram.settings.enableNodeNavigation"), this.enableNodeNavigationCheckBox)
                .addLabeledComponent(JALMessages.message("jal.instructionDiagram.settings.autoRefreshEnabled"), this.autoRefreshEnabledCheckBox)
                .addLabeledComponent(JALMessages.message("jal.instructionDiagram.settings.autoRefreshDelayMs"), this.autoRefreshDelaySpinner)
                .addLabeledComponent(JALMessages.message("jal.instructionDiagram.settings.resetViewOnRefresh"), this.resetViewOnRefreshCheckBox)
                .getPanel();

        JPanel controlsPanel = new JPanel(new BorderLayout(0, 8));
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.add(this.wrapSection(JALMessages.message("jal.instructionDiagram.settings.section.behavior"), behaviorPanel));
        contentPanel.add(this.wrapSection(JALMessages.message("jal.instructionDiagram.settings.section.visual"), visualPanel));
        controlsPanel.add(contentPanel, BorderLayout.NORTH);
        controlsPanel.setBorder(JBUI.Borders.empty(8));

        JBScrollPane scrollPane = new JBScrollPane(controlsPanel);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.setPreferredSize(JBUI.size(340, 0));
        return panel;
    }

    private @NotNull JPanel wrapSection(@NotNull String title, @NotNull JComponent content) {
        JLabel label = new JLabel(title);
        Font baseFont = label.getFont();
        label.setFont(baseFont.deriveFont(Font.BOLD));
        label.setBorder(JBUI.Borders.emptyBottom(6));

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(label, BorderLayout.NORTH);
        panel.add(content, BorderLayout.CENTER);
        panel.setBorder(JBUI.Borders.emptyBottom(12));
        return panel;
    }

    private void installListeners() {
        this.installSettingListener(this.showGridCheckBox);
        this.installSettingListener(this.showHintCheckBox);
        this.installSettingListener(this.enablePanCheckBox);
        this.installSettingListener(this.enableZoomCheckBox);
        this.installSettingListener(this.enableNodeNavigationCheckBox);
        this.installSettingListener(this.autoRefreshEnabledCheckBox);
        this.installSettingListener(this.resetViewOnRefreshCheckBox);
        this.autoRefreshDelaySpinner.addChangeListener(e -> this.publishSettings());
        this.backgroundColorButton.setOnChange(ignored -> this.publishSettings());
        this.gridColorButton.setOnChange(ignored -> this.publishSettings());
        this.methodBoxFillColorButton.setOnChange(ignored -> this.publishSettings());
        this.methodBoxBorderColorButton.setOnChange(ignored -> this.publishSettings());
        this.methodBoxTextColorButton.setOnChange(ignored -> this.publishSettings());
        this.instructionSetFillColorButton.setOnChange(ignored -> this.publishSettings());
        this.instructionSetBorderColorButton.setOnChange(ignored -> this.publishSettings());
        this.instructionSetTextColorButton.setOnChange(ignored -> this.publishSettings());
        this.nodeFillColorButton.setOnChange(ignored -> this.publishSettings());
        this.nodeTextColorButton.setOnChange(ignored -> this.publishSettings());
        this.dataEdgeColorButton.setOnChange(ignored -> this.publishSettings());
        this.controlEdgeColorButton.setOnChange(ignored -> this.publishSettings());
        this.neutralNodeBorderColorButton.setOnChange(ignored -> this.publishSettings());
        this.producerNodeBorderColorButton.setOnChange(ignored -> this.publishSettings());
        this.bothNodeBorderColorButton.setOnChange(ignored -> this.publishSettings());
        this.consumerNodeBorderColorButton.setOnChange(ignored -> this.publishSettings());
        this.jumpEdgeColor1Button.setOnChange(ignored -> this.publishSettings());
        this.jumpEdgeColor2Button.setOnChange(ignored -> this.publishSettings());
        this.jumpEdgeColor3Button.setOnChange(ignored -> this.publishSettings());
        this.jumpEdgeColor4Button.setOnChange(ignored -> this.publishSettings());
    }

    private void installSettingListener(@NotNull AbstractButton button) {
        button.addActionListener(e -> this.publishSettings());
    }

    private void publishSettings() {
        if (this.updating)
            return;
        this.onSettingsChanged.accept(new InstructionDiagramSettings(
                this.showGridCheckBox.isSelected(),
                this.showHintCheckBox.isSelected(),
                this.enablePanCheckBox.isSelected(),
                this.enableZoomCheckBox.isSelected(),
                this.enableNodeNavigationCheckBox.isSelected(),
                this.autoRefreshEnabledCheckBox.isSelected(),
                ((Number) this.autoRefreshDelaySpinner.getValue()).intValue(),
                this.resetViewOnRefreshCheckBox.isSelected(),
                this.toJBColor(this.backgroundColorButton.getColor()),
                this.toJBColor(this.gridColorButton.getColor()),
                this.toJBColor(this.methodBoxFillColorButton.getColor()),
                this.toJBColor(this.methodBoxBorderColorButton.getColor()),
                this.toJBColor(this.methodBoxTextColorButton.getColor()),
                this.toJBColor(this.instructionSetFillColorButton.getColor()),
                this.toJBColor(this.instructionSetBorderColorButton.getColor()),
                this.toJBColor(this.instructionSetTextColorButton.getColor()),
                this.toJBColor(this.nodeFillColorButton.getColor()),
                this.toJBColor(this.nodeTextColorButton.getColor()),
                this.toJBColor(this.dataEdgeColorButton.getColor()),
                this.toJBColor(this.controlEdgeColorButton.getColor()),
                this.toJBColor(this.neutralNodeBorderColorButton.getColor()),
                this.toJBColor(this.producerNodeBorderColorButton.getColor()),
                this.toJBColor(this.bothNodeBorderColorButton.getColor()),
                this.toJBColor(this.consumerNodeBorderColorButton.getColor()),
                this.toJBColor(this.jumpEdgeColor1Button.getColor()),
                this.toJBColor(this.jumpEdgeColor2Button.getColor()),
                this.toJBColor(this.jumpEdgeColor3Button.getColor()),
                this.toJBColor(this.jumpEdgeColor4Button.getColor())
        ));
    }

    private @NotNull JBColor toJBColor(@NotNull Color color) {
        if (color instanceof JBColor jbColor)
            return jbColor;
        return new JBColor(color, color);
    }

    private static final class ColorButton extends JButton {
        private Color color = JBColor.WHITE;
        private Consumer<Color> onChange = ignored -> {
        };

        private ColorButton() {
            this.setHorizontalAlignment(SwingConstants.LEFT);
            this.setFocusable(false);
            this.addActionListener(e -> {
                Color chosen = JColorChooser.showDialog(
                        this,
                        JALMessages.message("jal.instructionDiagram.settings.chooseColor"),
                        this.color
                );
                if (chosen == null)
                    return;
                this.setColor(chosen);
                this.onChange.accept(chosen);
            });
            this.refresh();
        }

        private void setOnChange(@NotNull Consumer<Color> onChange) {
            this.onChange = onChange;
        }

        private @NotNull Color getColor() {
            return this.color;
        }

        private void setColor(@NotNull Color color) {
            this.color = color;
            this.refresh();
        }

        private void refresh() {
            this.setBackground(this.color);
            this.setOpaque(true);
            this.setText(String.format("#%06X", this.color.getRGB() & 0xFFFFFF));
            this.setForeground(this.isDark(this.color) ? JBColor.WHITE : JBColor.BLACK);
        }

        private boolean isDark(@NotNull Color color) {
            int luminance = (color.getRed() * 299 + color.getGreen() * 587 + color.getBlue() * 114) / 1000;
            return luminance < 140;
        }
    }
}
