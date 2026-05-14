package tokyo.peya.javasm.intellij.instructiondiagram;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.Service.Level;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.ui.JBColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

@Service(Level.APP)
@State(name = "InstructionDiagramSettings", storages = @Storage("javasm-instruction-diagram.xml"))
public final class InstructionDiagramSettingsService
        implements PersistentStateComponent<InstructionDiagramSettingsService.StateBean> {
    private StateBean state = new StateBean();

    @Override
    public @Nullable StateBean getState() {
        return this.state;
    }

    @Override
    public void loadState(@NotNull StateBean state) {
        this.state = state;
    }

    public @NotNull InstructionDiagramSettings getSettings() {
        return new InstructionDiagramSettings(
                this.state.showGrid,
                this.state.showHint,
                this.state.enablePan,
                this.state.enableZoom,
                this.state.enableNodeNavigation,
                this.state.autoRefreshEnabled,
                Math.max(250, this.state.autoRefreshDelayMs),
                this.state.resetViewOnRefresh,
                this.toColor(this.state.backgroundColorRgb),
                this.toColor(this.state.gridColorRgb),
                this.toColor(this.state.methodBoxFillColorRgb),
                this.toColor(this.state.methodBoxBorderColorRgb),
                this.toColor(this.state.methodBoxTextColorRgb),
                this.toColor(this.state.instructionSetFillColorRgb),
                this.toColor(this.state.instructionSetBorderColorRgb),
                this.toColor(this.state.instructionSetTextColorRgb),
                this.toColor(this.state.nodeFillColorRgb),
                this.toColor(this.state.nodeTextColorRgb),
                this.toColor(this.state.dataEdgeColorRgb),
                this.toColor(this.state.controlEdgeColorRgb),
                this.toColor(this.state.neutralNodeBorderColorRgb),
                this.toColor(this.state.producerNodeBorderColorRgb),
                this.toColor(this.state.bothNodeBorderColorRgb),
                this.toColor(this.state.consumerNodeBorderColorRgb),
                this.toColor(this.state.jumpEdgeColor1Rgb),
                this.toColor(this.state.jumpEdgeColor2Rgb),
                this.toColor(this.state.jumpEdgeColor3Rgb),
                this.toColor(this.state.jumpEdgeColor4Rgb)
        );
    }

    public void setSettings(@NotNull InstructionDiagramSettings settings) {
        this.state.showGrid = settings.showGrid();
        this.state.showHint = settings.showHint();
        this.state.enablePan = settings.enablePan();
        this.state.enableZoom = settings.enableZoom();
        this.state.enableNodeNavigation = settings.enableNodeNavigation();
        this.state.autoRefreshEnabled = settings.autoRefreshEnabled();
        this.state.autoRefreshDelayMs = Math.max(1500, settings.autoRefreshDelayMs());
        this.state.resetViewOnRefresh = settings.resetViewOnRefresh();
        this.state.backgroundColorRgb = this.toRgb(settings.backgroundColor());
        this.state.gridColorRgb = this.toRgb(settings.gridColor());
        this.state.methodBoxFillColorRgb = this.toRgb(settings.methodBoxFillColor());
        this.state.methodBoxBorderColorRgb = this.toRgb(settings.methodBoxBorderColor());
        this.state.methodBoxTextColorRgb = this.toRgb(settings.methodBoxTextColor());
        this.state.instructionSetFillColorRgb = this.toRgb(settings.instructionSetFillColor());
        this.state.instructionSetBorderColorRgb = this.toRgb(settings.instructionSetBorderColor());
        this.state.instructionSetTextColorRgb = this.toRgb(settings.instructionSetTextColor());
        this.state.nodeFillColorRgb = this.toRgb(settings.nodeFillColor());
        this.state.nodeTextColorRgb = this.toRgb(settings.nodeTextColor());
        this.state.dataEdgeColorRgb = this.toRgb(settings.dataEdgeColor());
        this.state.controlEdgeColorRgb = this.toRgb(settings.controlEdgeColor());
        this.state.neutralNodeBorderColorRgb = this.toRgb(settings.neutralNodeBorderColor());
        this.state.producerNodeBorderColorRgb = this.toRgb(settings.producerNodeBorderColor());
        this.state.bothNodeBorderColorRgb = this.toRgb(settings.bothNodeBorderColor());
        this.state.consumerNodeBorderColorRgb = this.toRgb(settings.consumerNodeBorderColor());
        this.state.jumpEdgeColor1Rgb = this.toRgb(settings.jumpEdgeColor1());
        this.state.jumpEdgeColor2Rgb = this.toRgb(settings.jumpEdgeColor2());
        this.state.jumpEdgeColor3Rgb = this.toRgb(settings.jumpEdgeColor3());
        this.state.jumpEdgeColor4Rgb = this.toRgb(settings.jumpEdgeColor4());
    }

    private int toRgb(@NotNull Color color) {
        return color.getRGB() & 0xFFFFFF;
    }

    private @NotNull JBColor toColor(int rgb) {
        Color awt = new JBColor(new Color(rgb & 0xFFFFFF), new Color(rgb & 0xFFFFFF));
        return new JBColor(awt, awt);
    }

    public static final class StateBean {
        public boolean showGrid = true;
        public boolean showHint = true;
        public boolean enablePan = true;
        public boolean enableZoom = true;
        public boolean enableNodeNavigation = true;
        public boolean autoRefreshEnabled = true;
        public int autoRefreshDelayMs = 2500;
        public boolean resetViewOnRefresh;
        public int backgroundColorRgb = 0x1f2125;
        public int gridColorRgb = 0x2e3238;
        public int methodBoxFillColorRgb = 0x25282d;
        public int methodBoxBorderColorRgb = 0x5d6670;
        public int methodBoxTextColorRgb = 0xd1d8e0;
        public int instructionSetFillColorRgb = 0x2b2f35;
        public int instructionSetBorderColorRgb = 0x6c7580;
        public int instructionSetTextColorRgb = 0xc7d0da;
        public int nodeFillColorRgb = 0x474747;
        public int nodeTextColorRgb = 0xc8d1de;
        public int dataEdgeColorRgb = 0x62d26f;
        public int controlEdgeColorRgb = 0x8b939e;
        public int neutralNodeBorderColorRgb = 0x777f8a;
        public int producerNodeBorderColorRgb = 0x5f7f9d;
        public int bothNodeBorderColorRgb = 0x628c64;
        public int consumerNodeBorderColorRgb = 0xa17a53;
        public int jumpEdgeColor1Rgb = 0x4a90ff;
        public int jumpEdgeColor2Rgb = 0xd66cff;
        public int jumpEdgeColor3Rgb = 0xffc247;
        public int jumpEdgeColor4Rgb = 0x4dd6c7;
    }
}
