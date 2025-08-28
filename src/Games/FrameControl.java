package Games;

import javax.swing.*;
import java.awt.*;

public interface FrameControl {
    boolean shouldLockFrameSize();
    Dimension frameNewSize();
    void  gameContineu();

}
