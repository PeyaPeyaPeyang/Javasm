package tokyo.peya.javasm.intellij.stackviewer;

import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.EditorFontType;
import com.intellij.ui.JBColor;
import com.intellij.ui.JBSplitter;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.components.JBTextArea;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;

public class StackFramePanelFactory
{
    @Getter
    private final JPanel mainPanel;
    private final JPanel statusPanel;
    private final JBTextArea statusLabel;
    private final JProgressBar progressBar;
    @Getter
    private final ProgressbarUpdater updater;
    private final JButton analyseButton;
    private final JPanel controlPanel;
    private final JPanel localsPanel;
    private final JPanel stackPanel;

    private final JLabel instructionLabel;

    private boolean frameInfoRetrieved;

    public StackFramePanelFactory(@NotNull Runnable onAnalyseButtonClick)
    {
        this.statusPanel = new JPanel(new BorderLayout());
        this.statusLabel = createStatusLabel();
        this.progressBar = createProgressBar();
        this.analyseButton = createAnalyseButton(onAnalyseButtonClick);
        this.updater = new ProgressbarUpdater(this.progressBar, this.analyseButton, this.statusLabel);
        this.controlPanel = createControlPanel();
        this.stackPanel = createInitialStackPanel();
        this.localsPanel = createInitialStackPanel();
        this.instructionLabel = createInitialInstructionLabel();
        this.mainPanel = this.createMainPanel();
    }

    private JProgressBar createProgressBar()
    {
        JProgressBar progressBar = new JProgressBar();
        progressBar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 3));
        progressBar.setStringPainted(false);
        return progressBar;
    }

    private JButton createAnalyseButton(Runnable onClicK)
    {
        JButton analyseButton = new JButton("Analyse");
        analyseButton.setPreferredSize(new Dimension(100, 40)); // 幅を100pxに設定

        analyseButton.addActionListener(e -> {
            analyseButton.setEnabled(false);
            this.progressBar.setValue(0);
            onClicK.run();
        });

        return analyseButton;
    }

    private JPanel createControlPanel()
    {
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        controlPanel.setMaximumSize(new Dimension(Short.MAX_VALUE, 100));

        // progressBar と analyseButton を横並びに
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        bottomPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        bottomPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        bottomPanel.add(this.progressBar);
        bottomPanel.add(Box.createHorizontalStrut(5));
        bottomPanel.add(this.analyseButton);

        controlPanel.add(this.statusLabel);
        controlPanel.add(Box.createVerticalStrut(4));
        controlPanel.add(bottomPanel);
        controlPanel.setBackground(JBColor.BLUE);
        return controlPanel;
    }

    private JPanel createMainPanel()
    {
        // 初期化＆保持
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setPreferredSize(new Dimension(400, 300));


        // 上のパネル
        this.statusPanel.add(this.controlPanel, BorderLayout.WEST);
        mainPanel.add(this.statusPanel, BorderLayout.NORTH);

        // ====== 中央に上下分割の JBSplitter を配置 ======
        JBSplitter splitter = new JBSplitter(true, 0.5f); // true: 垂直方向（上下）, 0.5f: 分割割合
        splitter.setDividerWidth(5);

        JBScrollPane stackScrollPane = new JBScrollPane(this.localsPanel);
        stackScrollPane.setBorder(null);
        splitter.setFirstComponent(stackScrollPane);

        JBScrollPane localsScrollPane = new JBScrollPane(this.stackPanel);
        localsScrollPane.setBorder(null);
        splitter.setSecondComponent(localsScrollPane);

        // センターに splitter を追加！
        mainPanel.add(splitter, BorderLayout.CENTER);

        return mainPanel;
    }

    public void onEditorChanged()
    {
        if (!this.frameInfoRetrieved)
            return;

        this.frameInfoRetrieved = false;
        this.statusPanel.removeAll();
        this.statusPanel.add(this.controlPanel, BorderLayout.CENTER);
    }

    public void onUpdateCancelled()
    {
        this.frameInfoRetrieved = false;
        this.statusPanel.removeAll();
        this.statusPanel.add(this.controlPanel, BorderLayout.CENTER);

        this.instructionLabel.setText("Update cancelled.");
        this.progressBar.setIndeterminate(false);
        this.progressBar.setValue(0);
        this.analyseButton.setEnabled(true);
    }

    public void updateStackFrameInfo(@NotNull StackUIInstruction instruction,
                                     @NotNull StackUIElement[] stack,
                                     @NotNull StackUIElement[] locals)
    {
        this.frameInfoRetrieved = true;
        this.instructionLabel.setText(
                "Selecting: " + instruction.instruction() + " @ " + instruction.bytecodeOffset()
        );
        this.statusPanel.removeAll();
        this.statusPanel.add(this.instructionLabel, BorderLayout.WEST);

        updateStack(this.stackPanel, "Stack", stack);
        updateStack(this.localsPanel, "Locals", locals);
    }

    private static JBTextArea createStatusLabel()
    {
        JBTextArea statusLabel = new JBTextArea("Welcome! 'Analyse' computes stack frames.");
        Font editorFont = EditorColorsManager.getInstance().getGlobalScheme().getFont(EditorFontType.PLAIN);
        statusLabel.setFont(editorFont.deriveFont(editorFont.getSize() - 1f));
        statusLabel.setMaximumSize(new Dimension(Short.MAX_VALUE, 40)); // ← これ追加

        statusLabel.setLineWrap(true);                  // 行の折り返しを有効に
        statusLabel.setWrapStyleWord(true);             // 単語単位で折り返す
        statusLabel.setEditable(false);                 // 編集不可
        statusLabel.setOpaque(false);                   // 背景透過
        statusLabel.setFocusable(false);                // フォーカスしない
        statusLabel.setAlignmentX(Component.LEFT_ALIGNMENT); // 左揃え

        return statusLabel;
    }

    private static JLabel createInitialInstructionLabel()
    {
        Font editorFont = EditorColorsManager.getInstance().getGlobalScheme().getFont(EditorFontType.PLAIN);
        JLabel label = new JLabel("Selecting: NONE");
        label.setFont(editorFont.deriveFont(editorFont.getSize() + 2f).deriveFont(Font.BOLD));
        return label;
    }

    private static JPanel createInitialStackPanel()
    {
        JPanel stackPanel = new JPanel();
        stackPanel.setLayout(new BoxLayout(stackPanel, BoxLayout.Y_AXIS));
        stackPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        return stackPanel;
    }

    private static void updateStack(@NotNull JPanel panel, @NotNull String name, @NotNull StackUIElement[] stack)
    {
        panel.removeAll();

        Font editorFont = EditorColorsManager.getInstance().getGlobalScheme().getFont(EditorFontType.PLAIN);

        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel(name);
        // 10px
        titleLabel.setFont(editorFont.deriveFont(editorFont.getSize() + 2f).deriveFont(Font.BOLD));

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
        northPanel.add(new JSeparator());
        northPanel.add(titleLabel);

        headerPanel.add(northPanel, BorderLayout.NORTH);
        panel.add(headerPanel);

        for (int i = stack.length - 1; i >= 0; i--)
        {
            panel.add(createStackEntryFromUIElement(editorFont, stack[i]));
            if (i > 0) // 最後の要素以外はスペースを追加
                panel.add(Box.createVerticalStrut(5));
        }
        panel.add(createStackBase());

        panel.revalidate();
        panel.repaint();
    }

    private static JPanel createStackEntryFromUIElement(@NotNull Font editorFont, @NotNull StackUIElement elem)
    {
        Font font = editorFont.deriveFont(editorFont.getSize() - 1f);

        JBColor bg = elem.backgroundColor();
        JBColor textColor = elem.textColor();
        String text = elem.elementType();
        StackUIElement.DisplayType displayType = elem.displayType();

        return new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int w = getWidth();
                int h = getHeight();

                Stroke originalStroke = g2.getStroke();

                // 描画設定
                switch (displayType)
                {
                    case UNCHANGING ->
                    {
                        // 塗りつぶし: 背景色
                        g2.setColor(bg);
                        g2.fillRect(10, 0, w - 20, h);
                    }
                    case PUSH ->
                    {
                        // 実線枠のみ
                        g2.setColor(bg);
                        g2.drawRect(10, 0, w - 21, h - 1);
                    }
                    case POP ->
                    {
                        // 破線枠のみ
                        float[] dash = {4f, 4f};
                        g2.setStroke(new BasicStroke(1f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10f, dash, 0f));
                        g2.setColor(bg);
                        g2.drawRect(10, 0, w - 21, h - 1);
                        g2.setStroke(originalStroke); // 後の描画に影響しないように戻す
                    }
                }

                // テキスト描画
                g2.setFont(font);
                FontMetrics fm = g2.getFontMetrics();
                int textWidth = fm.stringWidth(text);
                int textHeight = fm.getAscent();
                int textX = (w - textWidth) / 2;
                int textY = (h + textHeight) / 2 - 2;

                // UNCHANGING では文字は textColor、それ以外は bg（コントラスト考慮）
                g2.setColor(displayType == StackUIElement.DisplayType.UNCHANGING ? textColor: bg);
                g2.drawString(text, textX, textY);

                if (displayType == StackUIElement.DisplayType.POP)
                {
                    // POP は 打ち消し線（strike-through）
                    int strikeY = (textY - fm.getAscent() / 2) + 2; // 中央あたり
                    g2.drawLine(textX, strikeY, textX + textWidth, strikeY);
                }
            }

            @Override
            public Dimension getPreferredSize()
            {
                return new Dimension(100, 25);
            }

            @Override
            public Dimension getMaximumSize()
            {
                return new Dimension(Integer.MAX_VALUE, 25);
            }
        };
    }

    private static @NotNull JPanel createStackBase()
    {
        JPanel basePanel = new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;

                g2.setColor(JBColor.GRAY);
                g2.setStroke(new BasicStroke(3));  // ← ここで線の太さ指定

                int width = this.getWidth();
                int height = this.getHeight();

                // 左右の縦線（|）
                g2.drawLine(5, 0, 5, height); // 左の |
                g2.drawLine(width - 6, 0, width - 6, height); // 右の |

                // 下の横線（_）
                g2.drawLine(5, height - 1, width - 6, height - 1); // 下線（_ _ _）
            }
        };
        basePanel.setPreferredSize(new Dimension(0, 10));
        basePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 10)); // 横幅自動
        basePanel.setAlignmentX(Component.CENTER_ALIGNMENT); // 中央揃え
        basePanel.setOpaque(false); // 背景透過でなじませる
        return basePanel;
    }

    @AllArgsConstructor
    public static class ProgressbarUpdater
    {
        private final JProgressBar progressBar;
        private final JButton button;
        private final JBTextArea statusLabel;

        public void setIndeterminate(boolean indeterminate)
        {
            this.progressBar.setIndeterminate(indeterminate);
            this.button.setEnabled(!indeterminate);
        }

        public void updateStatus(String status)
        {
            this.statusLabel.setText(status);
        }
    }
}
