package tokyo.peya.javasm.intellij.instructiondiagram;

import com.intellij.ui.JBColor;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public record InstructionDiagramSettings(
        boolean showGrid,
        boolean showHint,
        boolean enablePan,
        boolean enableZoom,
        boolean enableNodeNavigation,
        boolean autoRefreshEnabled,
        int autoRefreshDelayMs,
        boolean resetViewOnRefresh,
        @NotNull JBColor backgroundColor,
        @NotNull JBColor gridColor,
        @NotNull JBColor methodBoxFillColor,
        @NotNull JBColor methodBoxBorderColor,
        @NotNull JBColor methodBoxTextColor,
        @NotNull JBColor instructionSetFillColor,
        @NotNull JBColor instructionSetBorderColor,
        @NotNull JBColor instructionSetTextColor,
        @NotNull JBColor nodeFillColor,
        @NotNull JBColor nodeTextColor,
        @NotNull JBColor dataEdgeColor,
        @NotNull JBColor controlEdgeColor,
        @NotNull JBColor neutralNodeBorderColor,
        @NotNull JBColor producerNodeBorderColor,
        @NotNull JBColor bothNodeBorderColor,
        @NotNull JBColor consumerNodeBorderColor,
        @NotNull JBColor jumpEdgeColor1,
        @NotNull JBColor jumpEdgeColor2,
        @NotNull JBColor jumpEdgeColor3,
        @NotNull JBColor jumpEdgeColor4
) {
    public static @NotNull InstructionDiagramSettings defaults() {
        return new InstructionDiagramSettings(
                true,
                true,
                true,
                true,
                true,
                true,
                2500,
                false,
                solid(0x1f2125),
                solid(0x2e3238),
                solid(0x25282d),
                solid(0x5d6670),
                solid(0xd1d8e0),
                solid(0x2b2f35),
                solid(0x6c7580),
                solid(0xc7d0da),
                solid(0x474747),
                solid(0xc8d1de),
                solid(0x62d26f),
                solid(0x8b939e),
                solid(0x777f8a),
                solid(0x5f7f9d),
                solid(0x628c64),
                solid(0xa17a53),
                solid(0x4a90ff),
                solid(0xd66cff),
                solid(0xffc247),
                solid(0x4dd6c7)
        );
    }

    private static @NotNull JBColor solid(int rgb) {
        Color awt = new Color(rgb);
        return new JBColor(awt, awt);
    }

    public @NotNull JBColor[] jumpEdgeColors() {
        return new JBColor[]{this.jumpEdgeColor1, this.jumpEdgeColor2, this.jumpEdgeColor3, this.jumpEdgeColor4};
    }
}
