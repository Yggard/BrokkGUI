package net.voxelindustry.brokkgui.shape;

import net.voxelindustry.brokkgui.exp.component.GuiElement;
import net.voxelindustry.brokkgui.exp.component.Transform;
import net.voxelindustry.brokkgui.internal.IGuiRenderer;
import net.voxelindustry.brokkgui.paint.Color;
import net.voxelindustry.brokkgui.paint.Texture;
import net.voxelindustry.brokkgui.util.MouseInBoundsChecker;

public class CircleShape implements ShapeDefinition
{
    @Override
    public void drawColored(Transform transform, IGuiRenderer renderer, float startX, float startY, Color color,
                            float zLevel)
    {
        renderer.getHelper().drawColoredCircle(renderer, startX, startY, transform.width(), zLevel, color);
    }

    @Override
    public void drawColoredEmpty(Transform transform, IGuiRenderer renderer, float startX, float startY,
                                 float lineWidth,
                                 Color color, float zLevel)
    {
        renderer.getHelper().drawColoredEmptyCircle(renderer, startX, startY, transform.width(), zLevel, color,
                lineWidth);
    }

    @Override
    public void drawTextured(Transform transform, IGuiRenderer renderer, float startX, float startY, Texture texture,
                             float zLevel)
    {
        renderer.getHelper().drawTexturedCircle(renderer, startX, startY, texture.getUMin(), texture.getVMin(),
                texture.getUMax(), texture.getVMax(), transform.width(), zLevel);
    }

    @Override
    public boolean isMouseInside(GuiElement element, int mouseX, int mouseY)
    {
        if (!MouseInBoundsChecker.DEFAULT.test(element, mouseX, mouseY))
            return false;

        Transform transform = element.transform();
        float pointX = (mouseX - transform.xPos() - transform.xTranslate());
        float pointY = (mouseY - transform.yPos() - transform.yTranslate());
        return (pointX * pointX) + (pointY * pointY) < transform.width() * transform.width();
    }
}
