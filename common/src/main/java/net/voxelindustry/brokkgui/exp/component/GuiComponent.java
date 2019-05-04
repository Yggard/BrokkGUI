package net.voxelindustry.brokkgui.exp.component;

import fr.ourten.teabeans.value.BaseProperty;

public abstract class GuiComponent
{
    private BaseProperty<GuiElement> elementProperty;

    public GuiComponent()
    {

    }

    protected void attach(GuiElement element)
    {
        this.elementProperty.setValue(element);
    }

    public GuiElement element()
    {
        return this.elementProperty.getValue();
    }

    public boolean hasElement()
    {
        return this.elementProperty.isPresent();
    }

    public BaseProperty<GuiElement> getElementProperty()
    {
        return this.elementProperty;
    }
}
