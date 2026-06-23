package com.cafelumiere.ui.components;

import com.cafelumiere.ui.theme.Theme;
import com.k33ptoo.components.KButton;

import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

/**
 * Factory for KControls {@link KButton}s styled to the design-system Button
 * spec: primary (brown gradient), secondary (flat beige), danger (terracotta).
 * Sizes sm / md / lg control font and padding.
 *
 * Secondary and danger use a 2-colour gradient with identical start/end, which
 * renders as a flat solid fill — satisfying the Swing "flat fills" constraint
 * while reusing KButton's hover/press colour swaps.
 */
public final class Buttons {

    private Buttons() {}

    public enum Variant { PRIMARY, SECONDARY, DANGER }
    public enum Size { SM, MD, LG }

    public static KButton create(String text, Variant variant, Size size) {
        KButton b = new KButton();
        b.setText(text);
        b.setkAllowGradient(true);
        b.setkBorderRadius(Theme.RADIUS_MD * 2); // arc diameter ≈ 8px corner radius
        b.setFocusPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        switch (variant) {
            case PRIMARY -> {
                b.setkStartColor(Theme.BTN_PRIMARY_START);
                b.setkEndColor(Theme.BTN_PRIMARY_END);
                b.setkHoverColor(Theme.BTN_PRIMARY_HOVER);
                b.setkPressedColor(Theme.BTN_PRIMARY_PRESSED);
                b.setkForeGround(Theme.BTN_PRIMARY_TEXT);
                b.setkHoverForeGround(Theme.BTN_PRIMARY_TEXT);
            }
            case SECONDARY -> {
                flat(b, Theme.BTN_SECONDARY_BG, Theme.BTN_SECONDARY_HOVER,
                        Theme.BTN_SECONDARY_PRESSED, Theme.BTN_SECONDARY_TEXT);
            }
            case DANGER -> {
                flat(b, Theme.BTN_DANGER_BG, Theme.BTN_DANGER_HOVER,
                        Theme.BTN_DANGER_PRESSED, Theme.BTN_DANGER_TEXT);
            }
        }

        Font font;
        int padV, padH;
        switch (size) {
            case SM -> { font = Theme.captionBold(); padV = 6;  padH = 16; }
            case LG -> { font = Theme.bodyBold();    padV = 12; padH = 32; }
            default -> { font = Theme.label();        padV = 8;  padH = 24; }
        }
        b.setFont(font);
        b.setBorder(BorderFactory.createEmptyBorder(padV, padH, padV, padH));
        return b;
    }

    private static void flat(KButton b, Color bg, Color hover, Color pressed, Color fg) {
        b.setkStartColor(bg);
        b.setkEndColor(bg);
        b.setkHoverColor(hover);
        b.setkPressedColor(pressed);
        b.setkForeGround(fg);
        b.setkHoverForeGround(fg);
    }
}
