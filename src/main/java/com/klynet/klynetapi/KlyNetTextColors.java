package com.klynet.klynetapi;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;

public class KlyNetTextColors {

    public static final TextColor ERROR_COLOR = TextColor.color(191, 0, 4);
    public static final TextColor SUCCESS_COLOR = TextColor.color(29, 191, 0);
    public static final TextColor INFO_COLOR = TextColor.color(0, 191, 188);
    public static final TextColor UNIMPORTANT_COLOR = TextColor.color(128, 128, 128);

    public static TextComponent formatMessage(MessageType type, TextComponent component, String... keywords) {
        TextColor baseColor;
        TextColor keywordColor;

        switch (type) {
            case ERROR -> {
                baseColor = ERROR_COLOR;
                keywordColor = TextColor.color(255, 111, 112);
            }
            case INFO -> {
                baseColor = INFO_COLOR;
                keywordColor = TextColor.color(186, 248, 255);
            }
            case SUCCESS -> {
                baseColor = SUCCESS_COLOR;
                keywordColor = TextColor.color(170, 255, 133);
            }
            default -> throw new IllegalArgumentException("Invalid MessageType");
        }

        Style baseStyle = Style.style(baseColor);
        Style keywordStyle = Style.style(keywordColor);

        TextComponent.Builder builder = Component.text();
        String text = component.content();
        int lastIndex = 0;

        for (String keyword : keywords) {
            int index = text.indexOf(keyword, lastIndex);
            if (index != -1) {
                builder.append(Component.text(text.substring(lastIndex, index), baseStyle));
                builder.append(Component.text(keyword, keywordStyle));
                lastIndex = index + keyword.length();
            }
        }

        // Append the remaining text with the base style
        if (lastIndex < text.length()) {
            builder.append(Component.text(text.substring(lastIndex), baseStyle));
        }

        return builder.build();
    }

}
