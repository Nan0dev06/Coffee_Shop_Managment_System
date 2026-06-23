package com.cafelumiere.ui;

import com.cafelumiere.ui.components.Buttons;
import com.cafelumiere.ui.components.LabeledField;
import com.cafelumiere.ui.components.RoundedPanel;
import com.cafelumiere.ui.theme.Theme;
import com.k33ptoo.components.KButton;
import com.k33ptoo.components.KGradientPanel;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;

/**
 * Login screen: full brown-gradient backdrop with a centred white card holding
 * the brand, a username + password field and a Sign In button. No real
 * credential check — the button just enters the app.
 */
public class LoginScreen extends KGradientPanel {

    public LoginScreen(Runnable onLogin) {
        setkStartColor(Theme.BROWN_800);
        setkEndColor(Theme.BROWN_700);
        setkGradientFocus(600);
        setkFillBackground(true);
        setkBorderRadius(0);
        setLayout(new GridBagLayout()); // centres the single card child

        RoundedPanel card = new RoundedPanel();
        card.setFill(Theme.SURFACE_CARD).setBorderColor(null).setBorderThickness(0)
            .setRadius(Theme.RADIUS_XL);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(Theme.S48, 40, Theme.S48, 40));
        Dimension cardSize = new Dimension(360, 360);
        card.setPreferredSize(cardSize);
        card.setMaximumSize(cardSize);

        JLabel brand = new JLabel("Café Lumière");
        brand.setFont(Theme.font(Font.BOLD, 26));
        brand.setForeground(Theme.TEXT_PRIMARY);
        brand.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel sub = new JLabel("COFFEE MANAGEMENT");
        sub.setFont(Theme.caption());
        sub.setForeground(Theme.TEXT_SECONDARY);
        sub.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabeledField username = new LabeledField("Username", false, "Enter username");
        LabeledField password = new LabeledField("Password", true, null);
        username.setAlignmentX(Component.CENTER_ALIGNMENT);
        password.setAlignmentX(Component.CENTER_ALIGNMENT);
        username.setMaximumSize(new Dimension(Integer.MAX_VALUE, 56));
        password.setMaximumSize(new Dimension(Integer.MAX_VALUE, 56));

        KButton signIn = Buttons.create("Sign In", Buttons.Variant.PRIMARY, Buttons.Size.LG);
        signIn.setAlignmentX(Component.CENTER_ALIGNMENT);
        signIn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 46));
        signIn.addActionListener(e -> onLogin.run());

        card.add(brand);
        card.add(Box.createVerticalStrut(Theme.S8));
        card.add(sub);
        card.add(Box.createVerticalStrut(Theme.S32));
        card.add(username);
        card.add(Box.createVerticalStrut(Theme.S16));
        card.add(password);
        card.add(Box.createVerticalStrut(Theme.S24));
        card.add(signIn);

        add(card);
    }
}
