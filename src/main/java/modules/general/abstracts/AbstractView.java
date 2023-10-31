package modules.general.abstracts;

import data.general.Tuple;
import javafx.scene.control.Control;
import javafx.scene.control.Tooltip;
import modules.general.facades.IView;

public abstract class AbstractView implements IView
{
    @Override
    @SafeVarargs
    public final void initToolTips(Tuple<Control, String>... elements)
    {
        if (elements == null)
        {
            return;
        }

        // Iterate over each entry and set the tool tip text to the respective JavaFX element.
        for (Tuple<Control, String> element : elements)
        {
            // If any element is null, skip it.
            if (element == null || element.getVal1() == null || element.getVal2() == null)
            {
                continue;
            }

            element.getVal1().setTooltip(new Tooltip(element.getVal2()));
        }
    }
}
