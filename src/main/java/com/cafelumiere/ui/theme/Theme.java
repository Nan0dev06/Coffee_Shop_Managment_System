package com.cafelumiere.ui.theme;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.InputStream;

/**
 * Design tokens for Café Lumière, translated 1:1 from the imported Claude
 * design system (docs/design-system tokens/*.css). This is the single source
 * of truth for colors, spacing, radii and typography in the Swing prototype.
 *
 * 
 */
public final class Theme {

    private Theme() {}

    // ── Brown Scale (Primary) ──
    public static final Color BROWN_900 = hex("#3B2314");
    public static final Color BROWN_800 = hex("#4E2E1E");
    public static final Color BROWN_700 = hex("#5C3A27");
    public static final Color BROWN_600 = hex("#6F4E37");
    public static final Color BROWN_500 = hex("#7D5A43");
    public static final Color BROWN_400 = hex("#8B6B53");
    public static final Color BROWN_300 = hex("#A68B73");

    // ── Beige Scale ──
    public static final Color BEIGE_500 = hex("#C4A67D");
    public static final Color BEIGE_400 = hex("#D4B896");
    public static final Color BEIGE_300 = hex("#E0CAA8");
    public static final Color BEIGE_200 = hex("#EDD9BC");
    public static final Color BEIGE_100 = hex("#F5E6D3");
    public static final Color BEIGE_50  = hex("#FFF8F0");

    public static final Color WHITE = hex("#FFFFFF");

    // ── Surfaces ──
    public static final Color SURFACE_PAGE    = BEIGE_50;
    public static final Color SURFACE_CARD    = WHITE;
    public static final Color SURFACE_SIDEBAR = BROWN_800;
    public static final Color SURFACE_INPUT   = WHITE;
    public static final Color SURFACE_ALT_ROW = BEIGE_100;

    // ── Text ──
    public static final Color TEXT_PRIMARY       = BROWN_900;
    public static final Color TEXT_SECONDARY     = BROWN_400;
    public static final Color TEXT_ON_DARK       = BEIGE_50;
    public static final Color TEXT_ON_DARK_MUTED = BEIGE_300;

    // ── Buttons: primary ──
    public static final Color BTN_PRIMARY_START   = BROWN_700;
    public static final Color BTN_PRIMARY_END     = BROWN_600;
    public static final Color BTN_PRIMARY_HOVER   = BROWN_500;
    public static final Color BTN_PRIMARY_PRESSED = BROWN_800;
    public static final Color BTN_PRIMARY_TEXT    = WHITE;

    // ── Buttons: secondary ──
    public static final Color BTN_SECONDARY_BG      = BEIGE_200;
    public static final Color BTN_SECONDARY_HOVER   = BEIGE_300;
    public static final Color BTN_SECONDARY_PRESSED = BEIGE_400;
    public static final Color BTN_SECONDARY_TEXT    = BROWN_900;

    // ── Buttons: danger ──
    public static final Color BTN_DANGER_BG      = hex("#B7472A");
    public static final Color BTN_DANGER_HOVER   = hex("#9A3B22");
    public static final Color BTN_DANGER_PRESSED = hex("#7A2E1A");
    public static final Color BTN_DANGER_TEXT    = WHITE;

    // ── Feedback / Semantic ──
    public static final Color WARNING      = hex("#B7472A");
    public static final Color WARNING_BG   = hex("#FDECEA");
    public static final Color WARNING_TEXT = hex("#7A2E1A");
    public static final Color SUCCESS      = hex("#5B7A3A");
    public static final Color SUCCESS_BG   = hex("#EDF5E1");

    // ── Borders ──
    public static final Color BORDER_LIGHT  = BEIGE_300;
    public static final Color BORDER_MEDIUM = BEIGE_400;
    public static final Color BORDER_DARK   = BROWN_300;

    // ── Chart Bar Colors (cycle for >5 bars) ──
    public static final Color[] CHART_COLORS = {
        hex("#6F4E37"), hex("#D4B896"), hex("#8B6B53"), hex("#5C3A27"), hex("#C4A67D")
    };

    // ── Spacing Scale (px) ──
    public static final int S2 = 2, S4 = 4, S8 = 8, S12 = 12, S16 = 16,
                            S24 = 24, S32 = 32, S48 = 48, S64 = 64;

    // ── Border Radii (px) ──
    public static final int RADIUS_SM = 4, RADIUS_MD = 8, RADIUS_LG = 12, RADIUS_XL = 16;
    public static final int RADIUS_ROUND = 9999;

    // ── Layout Constants ──
    public static final int SIDEBAR_WIDTH = 240;
    public static final int CARD_MIN_WIDTH = 200;
    public static final int ROW_MIN_HEIGHT = 44;
    public static final int ICON_SIZE = 20;

    // ── Typography (Swing pt sizes per typography.css) ──
    public static final int SIZE_DISPLAY = 28, SIZE_HEADING = 22, SIZE_SUBHEADING = 16,
                            SIZE_BODY = 14, SIZE_LABEL = 12, SIZE_CAPTION = 11;

    private static String fontFamily = "SansSerif"; // fallback until fonts load

    /**
     * Loads the bundled Nunito TTFs and registers them with the graphics
     * environment. Falls back to SansSerif if the font files are missing.
     * Call once at startup before building UI.
     */
    public static void loadFonts() {
        boolean loaded = registerFont("/fonts/Nunito-400.ttf")
                       & registerFont("/fonts/Nunito-600.ttf")
                       & registerFont("/fonts/Nunito-700.ttf");
        if (loaded) {
            fontFamily = "Nunito";
        }
    }

    private static boolean registerFont(String resource) {
        try (InputStream in = Theme.class.getResourceAsStream(resource)) {
            if (in == null) return false;
            Font font = Font.createFont(Font.TRUETYPE_FONT, in);
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // ── Font helpers (weight via Font style; Nunito static faces are registered
    //    under the same family, so PLAIN/BOLD select regular/bold; semibold maps
    //    to BOLD as Swing has no semibold style) ──
    public static Font font(int style, int size) {
        return new Font(fontFamily, style, size);
    }

    public static Font display()    { return font(Font.BOLD,  SIZE_DISPLAY); }
    public static Font heading()    { return font(Font.BOLD,  SIZE_HEADING); }
    public static Font subheading() { return font(Font.BOLD,  SIZE_SUBHEADING); }
    public static Font body()       { return font(Font.PLAIN, SIZE_BODY); }
    public static Font bodyBold()   { return font(Font.BOLD,  SIZE_BODY); }
    public static Font label()      { return font(Font.BOLD,  SIZE_LABEL); }
    public static Font labelPlain() { return font(Font.PLAIN, SIZE_LABEL); }
    public static Font caption()    { return font(Font.PLAIN, SIZE_CAPTION); }
    public static Font captionBold(){ return font(Font.BOLD,  SIZE_CAPTION); }

    private static Color hex(String h) {
        return new Color(
            Integer.parseInt(h.substring(1, 3), 16),
            Integer.parseInt(h.substring(3, 5), 16),
            Integer.parseInt(h.substring(5, 7), 16)
        );
    }
}
