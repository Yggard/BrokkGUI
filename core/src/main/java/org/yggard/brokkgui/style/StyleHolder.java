package org.yggard.brokkgui.style;

import java.util.HashMap;

import fr.ourten.teabeans.value.BaseProperty;

public class StyleHolder
{
    private HashMap<String, StyleableProperty<?>> properties;
    private BaseProperty<ICascadeStyleable>       parent;

    public StyleHolder(BaseProperty<ICascadeStyleable> parent)
    {
        this.properties = new HashMap<>();
        this.parent = parent;
    }

    public void parseCSS(String css)
    {
        for (String property : css.split(";"))
        {
            String[] splitted = property.split(":");
            String propertyName = splitted[0].trim();

            if (properties.containsKey(propertyName))
            {
                StyleableProperty<?> styleProp = properties.get(propertyName);
                styleProp.setValue(StyleDecoder.getInstance().decode(splitted[1].trim(), styleProp.getValueClass()));
            }
        }
    }

    public <T> void registerProperty(String name, T defaultValue, Class<T> valueClass)
    {
        this.properties.put(name, new StyleableProperty<>(defaultValue, name, valueClass));
    }

    public <T> StyleableProperty<T> getStyleProperty(String name, Class<T> clazz)
    {
        return (StyleableProperty<T>) this.properties.get(name);
    }

    /**
     * @return the parent
     */
    public BaseProperty<ICascadeStyleable> getParent()
    {
        return parent;
    }
}
