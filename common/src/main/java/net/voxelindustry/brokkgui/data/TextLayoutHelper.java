package net.voxelindustry.brokkgui.data;

import fr.ourten.teabeans.binding.BaseBinding;
import fr.ourten.teabeans.binding.Binding;
import net.voxelindustry.brokkgui.BrokkGuiPlatform;
import net.voxelindustry.brokkgui.component.GuiElement;
import net.voxelindustry.brokkgui.component.impl.Icon;
import net.voxelindustry.brokkgui.component.impl.Text;

import javax.annotation.Nullable;

public class TextLayoutHelper
{
    public static Binding<Float> createXPosBinding(GuiElement element, Text text, Icon icon, Binding<String> ellipsedTextProperty)
    {
        return new BaseBinding<Float>()
        {
            {
                super.bind(text.textAlignmentProperty(),
                        element.transform().widthProperty(),
                        icon.iconProperty(),
                        icon.iconSideProperty(),
                        icon.iconPaddingProperty(),
                        text.textPaddingProperty(),
                        ellipsedTextProperty);
            }

            @Override
            public Float computeValue()
            {
                float iconWidth = 0;
                if (icon.iconProperty().isPresent() && icon.iconSide().isHorizontal())
                    iconWidth = icon.icon().width() + icon.iconPadding();

                if (text.textAlignment().isLeft())
                    return text.textPadding().getLeft()
                            + (icon.iconSide() == RectSide.LEFT ? iconWidth : 0);
                else if (text.textAlignment().isRight())
                    return element.width()
                            - text.textPadding().getRight()
                            - BrokkGuiPlatform.instance().guiHelper().getStringWidth(ellipsedTextProperty.getValue())
                            - (icon.iconSide() == RectSide.RIGHT ? iconWidth : 0);
                else
                    return text.textPadding().getLeft()
                            + (icon.iconSide() == RectSide.LEFT ? iconWidth : 0)
                            + getAvailableTextWidth(element, text, icon) / 2
                            - BrokkGuiPlatform.instance().guiHelper().getStringWidth(ellipsedTextProperty.getValue()) / 2;
            }
        };
    }

    public static Binding<Float> createYPosBinding(GuiElement element, Text text, Icon icon)
    {
        return new BaseBinding<Float>()
        {
            {
                super.bind(text.textAlignmentProperty(),
                        element.transform().heightProperty(),
                        icon.iconProperty(),
                        icon.iconSideProperty(),
                        icon.iconPaddingProperty(),
                        text.textPaddingProperty());
            }

            @Override
            public Float computeValue()
            {
                float iconHeight = 0;
                if (icon.iconProperty().isPresent() && icon.iconSide().isVertical())
                    iconHeight = icon.icon().height() + icon.iconPadding();

                if (text.textAlignment().isUp())
                    return text.textPadding().getTop()
                            + (icon.iconSide() == RectSide.UP ? iconHeight : 0);
                else if (text.textAlignment().isDown())
                    return element.height()
                            - text.textPadding().getBottom()
                            - BrokkGuiPlatform.instance().guiHelper().getStringHeight()
                            - (icon.iconSide() == RectSide.DOWN ? iconHeight : 0);
                else
                    return element.height() / 2
                            + (icon.iconSide() == RectSide.UP ? iconHeight :
                            (icon.iconSide() == RectSide.DOWN ? -iconHeight : 0)) / 2
                            - BrokkGuiPlatform.instance().guiHelper().getStringHeight() / 2
                            + text.textPadding().getTop();
            }
        };
    }

    public static Binding<String> createEllipsedTextBinding(GuiElement element, Text text, Icon icon)
    {
        return new BaseBinding<String>()
        {
            {
                super.bind(text.textProperty(),
                        text.expandToTextProperty(),
                        element.transform().widthProperty(),
                        text.ellipsisProperty(),
                        text.textPaddingProperty(),
                        icon.iconPaddingProperty(),
                        icon.iconSideProperty(),
                        icon.iconProperty());
            }

            @Override
            public String computeValue()
            {
                if (!text.expandToText() && element.width() < getExpandedWidth(text, icon))
                {
                    String trimmed = BrokkGuiPlatform.instance().guiHelper().trimStringToPixelWidth(
                            text.text(), (int) (getAvailableTextWidth(element, text, icon)));

                    if (trimmed.length() < text.ellipsis().length())
                        return "";
                    trimmed = trimmed.substring(0, trimmed.length() - text.ellipsis().length());
                    return trimmed + text.ellipsis();
                }
                return text.text();
            }
        };
    }

    public static Binding<Float> createXPosBinding(GuiElement element, Text text, Binding<String> ellipsedTextProperty)
    {
        return new BaseBinding<Float>()
        {
            {
                super.bind(text.textAlignmentProperty(),
                        element.transform().widthProperty(),
                        text.textPaddingProperty(),
                        ellipsedTextProperty);
            }

            @Override
            public Float computeValue()
            {
                if (text.textAlignment().isLeft())
                    return text.textPadding().getLeft();
                else if (text.textAlignment().isRight())
                    return element.width()
                            - text.textPadding().getRight()
                            - BrokkGuiPlatform.instance().guiHelper().getStringWidth(ellipsedTextProperty.getValue());
                else
                    return text.textPadding().getLeft()
                            + getAvailableTextWidth(element, text, null) / 2
                            - BrokkGuiPlatform.instance().guiHelper().getStringWidth(ellipsedTextProperty.getValue()) / 2;
            }
        };
    }

    public static Binding<Float> createYPosBinding(GuiElement element, Text text)
    {
        return new BaseBinding<Float>()
        {
            {
                super.bind(text.textAlignmentProperty(),
                        element.transform().heightProperty(),
                        text.textPaddingProperty());
            }

            @Override
            public Float computeValue()
            {
                if (text.textAlignment().isUp())
                    return text.textPadding().getTop();
                else if (text.textAlignment().isDown())
                    return element.height()
                            - text.textPadding().getBottom()
                            - BrokkGuiPlatform.instance().guiHelper().getStringHeight();
                else
                    return element.height() / 2
                            - BrokkGuiPlatform.instance().guiHelper().getStringHeight() / 2
                            + text.textPadding().getTop();
            }
        };
    }

    public static Binding<String> createEllipsedTextBinding(GuiElement element, Text text)
    {
        return new BaseBinding<String>()
        {
            {
                super.bind(text.textProperty(),
                        text.expandToTextProperty(),
                        element.transform().widthProperty(),
                        text.ellipsisProperty(),
                        text.textPaddingProperty());
            }

            @Override
            public String computeValue()
            {
                if (!text.expandToText() && element.width() < getExpandedWidth(text, null))
                {
                    String trimmed = BrokkGuiPlatform.instance().guiHelper().trimStringToPixelWidth(
                            text.text(), (int) (getAvailableTextWidth(element, text, null)));

                    if (trimmed.length() < text.ellipsis().length())
                        return "";
                    trimmed = trimmed.substring(0, trimmed.length() - text.ellipsis().length());
                    return trimmed + text.ellipsis();
                }
                return text.text();
            }
        };
    }

    private static float getExpandedWidth(Text text, @Nullable Icon icon)
    {
        if (icon != null && icon.iconProperty().isPresent())
        {
            if (icon.iconSide().isHorizontal())
                return BrokkGuiPlatform.instance().guiHelper().getStringWidth(text.text())
                        + text.textPadding().getLeft() + text.textPadding().getRight()
                        + icon.icon().width() + icon.iconPadding();
            else
                return Math.max(BrokkGuiPlatform.instance().guiHelper().getStringWidth(text.text()),
                        icon.icon().width())
                        + text.textPadding().getLeft() + text.textPadding().getRight();
        }
        return BrokkGuiPlatform.instance().guiHelper().getStringWidth(text.text())
                + text.textPadding().getLeft() + text.textPadding().getRight();
    }

    private static float getAvailableTextWidth(GuiElement element, Text text, @Nullable Icon icon)
    {
        if (icon.iconProperty().isPresent() && icon.iconSide().isHorizontal())
        {
            return element.width()
                    - text.textPadding().getLeft() - text.textPadding().getRight()
                    - icon.icon().width() - icon.iconPadding();
        }
        return element.width() - text.textPadding().getLeft() - text.textPadding().getRight();
    }
}