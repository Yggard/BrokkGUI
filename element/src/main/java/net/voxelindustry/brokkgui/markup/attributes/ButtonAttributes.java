package net.voxelindustry.brokkgui.markup.attributes;

import net.voxelindustry.brokkgui.markup.MarkupAttribute;
import net.voxelindustry.brokkgui.markup.MarkupAttributesGroup;

import java.util.ArrayList;
import java.util.List;

public class ButtonAttributes implements MarkupAttributesGroup
{
    private static final ButtonAttributes instance = new ButtonAttributes();

    public static ButtonAttributes instance()
    {
        return instance;
    }

    private ButtonAttributes()
    {

    }

    private final List<MarkupAttribute> attributes         = new ArrayList<>();
    private final List<MarkupAttribute> childrenAttributes = new ArrayList<>();

    @Override
    public List<MarkupAttribute> getAttributes()
    {
        if (attributes.isEmpty())
            createAttributes();
        return attributes;
    }

    @Override
    public List<MarkupAttribute> getChildrenAttributes()
    {
        if (childrenAttributes.isEmpty())
            createChildrenAttributes();
        return childrenAttributes;
    }

    private void createAttributes()
    {
    }

    private void createChildrenAttributes()
    {
    }
}