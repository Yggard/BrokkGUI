package net.voxelindustry.brokkgui.element;

import fr.ourten.teabeans.property.Property;
import net.voxelindustry.brokkgui.component.GuiElement;
import net.voxelindustry.brokkgui.component.IGuiPopup;
import net.voxelindustry.brokkgui.data.RelativeBindingHelper;
import net.voxelindustry.brokkgui.element.pane.GuiPane;
import net.voxelindustry.brokkgui.internal.IRenderCommandReceiver;
import net.voxelindustry.brokkgui.style.StyledElement;

public class GuiToast extends GuiElement implements StyledElement, IGuiPopup
{
    private final Property<GuiElement> contentProperty;
    private final Property<Long>       lifeTimeProperty;
    private final Property<Long>       currentTimeProperty;

    private long millisStart;

    public GuiToast(GuiElement content, long lifeTime)
    {
        contentProperty = new Property<>(null);

        contentProperty.addChangeListener((obs, oldValue, newValue) ->
        {
            if (oldValue != null)
            {
                removeChild(oldValue);
                transform().widthProperty().unbind();
                transform().heightProperty().unbind();
                oldValue.transform().xPosProperty().unbind();
                oldValue.transform().yPosProperty().unbind();
            }

            if (newValue != null)
            {
                transform().addChild(newValue.transform());
                RelativeBindingHelper.bindToPos(newValue.transform(), transform());
                transform().widthProperty().bindProperty(newValue.transform().widthProperty());
                transform().heightProperty().bindProperty(newValue.transform().heightProperty());
            }
        });

        contentProperty.setValue(content);

        lifeTimeProperty = new Property<>(lifeTime);
        currentTimeProperty = new Property<>(0L);
    }

    public GuiToast(long lifeTime)
    {
        this(new GuiPane(), lifeTime);
    }

    @Override
    public void renderContent(IRenderCommandReceiver renderer, float mouseX, float mouseY)
    {
        super.renderContent(renderer, mouseX, mouseY);

        if (millisStart == 0)
            millisStart = System.currentTimeMillis();
        currentTimeProperty.setValue((System.currentTimeMillis() - millisStart));
    }

    @Override
    public String type()
    {
        return "toast";
    }

    public Property<GuiElement> getContentProperty()
    {
        return contentProperty;
    }

    public Property<Long> getLifeTimeProperty()
    {
        return lifeTimeProperty;
    }

    public Property<Long> getCurrentTimeProperty()
    {
        return currentTimeProperty;
    }

    public GuiElement getContent()
    {
        return getContentProperty().getValue();
    }

    public void setContent(GuiElement content)
    {
        getContentProperty().setValue(content);
    }

    public long getLifeTime()
    {
        return getLifeTimeProperty().getValue();
    }

    public void setLifeTime(long lifeTime)
    {
        getLifeTimeProperty().setValue(lifeTime);
    }

    public long getCurrentTime()
    {
        return getCurrentTimeProperty().getValue();
    }

    public void resetCurrentTime()
    {
        getCurrentTimeProperty().setValue(0L);
        millisStart = 0;
    }

    private void addCurrentTime(long currentLife)
    {
        getCurrentTimeProperty().setValue(getCurrentTime() + currentLife);
    }
}
