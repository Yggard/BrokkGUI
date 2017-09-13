package org.yggard.brokkgui.style;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.yggard.brokkgui.style.tree.*;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class StylesheetManagerTest
{
    @Test
    public void parseSimpleCSS()
    {
        StyleTree tree = null;
        try
        {
            tree = StylesheetManager.getInstance().loadStylesheet("/assets/brokkgui/css/test.css");
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        assertThat(tree).isNotNull();
        assertThat(tree.getInternalTree().getChildren(tree.getWildcard())).hasSize(2);

        Optional<StyleEntry> pane = tree.getInternalTree().getChildren(tree.getWildcard()).stream().filter(entry ->
                entry.getSelector().getType().equals(StyleSelectorType.TYPE)).findFirst();

        assertThat(pane).isPresent();
        assertThat(pane.get().getSelector().getType()).isEqualTo(StyleSelectorType.TYPE);
        assertThat(pane.get().getSelector().getSelector()).isEqualTo("pane");
        assertThat(pane.get().getRules()).contains(new StyleRule("-background-color", "red"), new StyleRule
                ("-border-color", "blue"), new StyleRule("-border-width", "1"));

    /*    assertThat(tree.getInternalTree().getChildren(pane.get())).hasSize(1);
        assertThat(tree.getInternalTree().getChildren(pane.get()).get(0).getSelector().getSelector()).containsEntry
                (StyleSelector.StyleSelectorType.CLASS, Collections.singletonList("snowflakes")).containsEntry
                (StyleSelector.StyleSelectorType.TYPE, Collections.singletonList("pane"));
        assertThat(tree.getInternalTree().getChildren(pane.get()).get(0).getRules()).contains(new StyleRule
                ("-border-color", "khaki"));

        Optional<StyleEntry> cyan = tree.getInternalTree().getChildren(tree.getWildcard()).stream().filter(entry ->
                entry.getSelector().getSelector().containsKey(StyleSelector.StyleSelectorType.ID)).findFirst();

        assertThat(cyan).isPresent();
        assertThat(cyan.get().getSelector().getSelector()).containsEntry(StyleSelector.StyleSelectorType.ID,
                Collections.singletonList("cyan"));
        assertThat(cyan.get().getRules()).contains(new StyleRule("-background-color", "cyan"));

        assertThat(tree.getInternalTree().getChildren(cyan.get())).hasSize(1);
        assertThat(tree.getInternalTree().getChildren(cyan.get()).get(0).getSelector().getSelector()).containsEntry
                (StyleSelector.StyleSelectorType.PSEUDOCLASS, Collections.singletonList("hover")).containsEntry
                (StyleSelector.StyleSelectorType.ID, Collections.singletonList("cyan"));
        assertThat(tree.getInternalTree().getChildren(cyan.get()).get(0).getRules()).contains(new StyleRule
                ("-background-color", "lightblue"));*/
    }
}
