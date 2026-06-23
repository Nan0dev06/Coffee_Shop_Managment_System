# Café Lumière Design System

## Overview

**Café Lumière** is a Java Swing desktop application for coffee shop management — a university OOP class project. This design system defines the complete visual language: color tokens, typography, spacing, component specs, and screen layouts.

| | |
|---|---|
| **Runtime** | Java Swing (AWT) |
| **UI Library** | KControls (KButton, KGradientPanel) |
| **Charting** | XChart (bar charts only) |
| **Palette** | Brown · Beige · White |
| **Aesthetic** | Warm café — think specialty coffee shop branding |

### Screens

1. **Login** — Single owner login, no multi-user roles
2. **Dashboard** — Stat cards (Revenue, Orders, Customers, Avg Order Value) + popular-drink bar chart + recent orders list
3. **Order Entry** — Customer selection + 5 fixed drink cards with circular product photo, type/quantity, add-to-cart
4. **Inventory** — Ingredient stock list, low-stock warning badge, restock action
5. **Revenue Summary** — Same stat-card style as dashboard, scoped to a date

### Explicitly Not in Scope

- Order status badges / workflow (orders have no status field)
- Add / edit / delete menu items (5 drinks are fixed)
- User account management, profile dropdowns, multi-user login
- Customer-spending analytics, search bars, pagination

---

## Color Tokens

### Brown Scale (Primary)

| Token | Hex | Usage |
|---|---|---|
| `brown-900` | `#3B2314` | Primary text on light backgrounds |
| `brown-800` | `#4E2E1E` | Sidebar bg, gradient dark end, pressed button |
| `brown-700` | `#5C3A27` | Primary button gradient start |
| `brown-600` | `#6F4E37` | **Brand base brown**, primary button gradient end |
| `brown-500` | `#7D5A43` | Button hover state |
| `brown-400` | `#8B6B53` | Muted / secondary text |
| `brown-300` | `#A68B73` | Borders on dark surfaces |

### Beige Scale

| Token | Hex | Usage |
|---|---|---|
| `beige-500` | `#C4A67D` | Strong beige, chart accent |
| `beige-400` | `#D4B896` | Base beige, input borders |
| `beige-300` | `#E0CAA8` | Light border, hover bg, gridlines |
| `beige-200` | `#EDD9BC` | Secondary button fill, card alt bg |
| `beige-100` | `#F5E6D3` | Alternating table row, light surface |
| `beige-50`  | `#FFF8F0` | Page / panel background |

### Other

| Token | Hex | Usage |
|---|---|---|
| `white` | `#FFFFFF` | Card surface, input fields |
| `warning` | `#B7472A` | Low-stock alerts (terracotta red) |
| `warning-bg` | `#FDECEA` | Warning background tint |
| `warning-text` | `#7A2E1A` | Warning label text |
| `success` | `#5B7A3A` | Positive / in-stock (olive green) |
| `success-bg` | `#EDF5E1` | Success background tint |

### Gradient Pairs (KGradientPanel / KButton)

| Element | Start | End | Direction |
|---|---|---|---|
| Sidebar | `#4E2E1E` | `#5C3A27` | Top → Bottom |
| Primary Button | `#5C3A27` | `#6F4E37` | Left → Right |
| Stat Card accent | `#6F4E37` | `#8B6B53` | Top → Bottom |

### Chart Bar Colors (5 Drinks)

| Bar | Hex |
|---|---|
| Drink 1 | `#6F4E37` |
| Drink 2 | `#D4B896` |
| Drink 3 | `#8B6B53` |
| Drink 4 | `#5C3A27` |
| Drink 5 | `#C4A67D` |

Label text: `#3B2314` · Background: `#FFFFFF` · Gridlines: `#E0CAA8`

---

## Typography

**Font:** Nunito (Google Fonts — download TTF, bundle in Swing via `Font.createFont`)  
**Swing fallback:** `SansSerif` logical font

| Role | Size (pt) | Weight | Web px |
|---|---|---|---|
| Display | 28 pt | Bold 700 | 36 px |
| Heading | 22 pt | Bold 700 | 28 px |
| Subheading | 16 pt | SemiBold 600 | 20 px |
| Body | 14 pt | Regular 400 | 16 px |
| Label | 12 pt | Medium 500 | 14 px |
| Caption | 11 pt | Regular 400 | 12 px |

**Text pairing:**
- On light backgrounds → `#3B2314`
- On dark backgrounds (sidebar, buttons) → `#FFF8F0`
- Muted / secondary → `#8B6B53`

---

## Spacing Scale

| Token | Value | Usage |
|---|---|---|
| `space-4` | 4 px | Tight gaps (icon ↔ label) |
| `space-8` | 8 px | Inline spacing, button padding-y |
| `space-12` | 12 px | Small padding |
| `space-16` | 16 px | Default padding, card inner |
| `space-24` | 24 px | Section spacing, card gaps |
| `space-32` | 32 px | Panel padding |
| `space-48` | 48 px | Screen-edge margins |

### Border Radii

| Token | Value | Usage |
|---|---|---|
| `radius-sm` | 4 px | Table cells, small chips |
| `radius-md` | 8 px | Buttons, inputs, nav items |
| `radius-lg` | 12 px | Stat cards, drink cards |
| `radius-xl` | 16 px | Large panels, dialogs |
| `radius-round` | 9999 px | Circular product photos, pill badges |

---

## Component Specs

### Sidebar Navigation

| Property | Value |
|---|---|
| Width | 240 px |
| Background | KGradientPanel `#4E2E1E` → `#5C3A27` (top → bottom) |
| Padding | 16 px vertical |
| Brand area | "Café Lumière" — 22 pt Bold, `#FFF8F0`, 32 px bottom margin |
| Nav item height | 48 px |
| Nav item padding | 16 px horizontal |
| Icon ↔ label gap | 8 px |
| Label style | 14 pt Medium, `#E0CAA8` |
| Active state | bg `#6F4E37`, text `#FFFFFF`, radius 8 px |
| Hover state | bg `#5C3A27`, text `#FFF8F0` |
| Logout | Bottom-pinned, warning color on hover (`#B7472A`) |

### Stat Card

| Property | Value |
|---|---|
| Min width | 200 px |
| Background | `#FFFFFF` |
| Border | 1 px solid `#E0CAA8` |
| Border radius | 12 px |
| Padding | 24 px |
| Left accent | 4 px wide, `#6F4E37` → `#8B6B53` gradient (optional) |
| Label | 12 pt Medium, `#8B6B53` |
| Value | 22 pt Bold, `#3B2314` |

### Button (KButton Mapping)

**Primary**

| KButton property | Value |
|---|---|
| `kStartColor` | `#5C3A27` |
| `kEndColor` | `#6F4E37` |
| `kHoverColor` | `#7D5A43` |
| `kPressedColor` | `#4E2E1E` |
| `foreground` | `#FFFFFF` |
| `kBorderRadius` | 8 |
| Font | Nunito 14 pt SemiBold |
| Padding | 8 px v · 24 px h |

**Secondary**

| KButton property | Value |
|---|---|
| `kStartColor` | `#EDD9BC` |
| `kEndColor` | `#EDD9BC` |
| `kHoverColor` | `#E0CAA8` |
| `kPressedColor` | `#D4B896` |
| `foreground` | `#3B2314` |
| `kBorderRadius` | 8 |

**Danger**

| KButton property | Value |
|---|---|
| `kStartColor` | `#B7472A` |
| `kEndColor` | `#B7472A` |
| `kHoverColor` | `#9A3B22` |
| `kPressedColor` | `#7A2E1A` |
| `foreground` | `#FFFFFF` |
| `kBorderRadius` | 8 |

### Drink Card (Order Entry)

| Property | Value |
|---|---|
| Width | ~200 px |
| Background | `#FFFFFF` |
| Border | 1 px solid `#E0CAA8` |
| Border radius | 12 px |
| Padding | 16 px |
| Image | 120 × 120 px circle, placeholder bg `#F5E6D3` |
| Title | 16 pt SemiBold, `#3B2314`, centered |
| Price | 14 pt Regular, `#8B6B53`, centered |
| Add button | Full-width Primary KButton — "Add to Cart" |

### Table / List Row

| Property | Value |
|---|---|
| Header bg | `#F5E6D3` |
| Header text | 12 pt SemiBold, `#3B2314` |
| Body rows | Alternating `#FFFFFF` / `#FFF8F0` |
| Row height | 44 px minimum |
| Cell padding | 12 px h · 8 px v |
| Row border | 1 px solid `#E0CAA8` (horizontal only) |
| Body text | 14 pt Regular, `#3B2314` |

### Low-Stock Badge

| Property | Value |
|---|---|
| Background | `#FDECEA` |
| Text | `#B7472A`, 11 pt SemiBold |
| Border radius | 9999 px (pill) |
| Padding | 4 px v · 12 px h |

### Bar Chart (XChart)

| Property | Value |
|---|---|
| Bar colors | See "Chart Bar Colors" above |
| Chart bg | `#FFFFFF` |
| Axis labels | 12 pt Regular, `#3B2314` |
| Gridlines | 1 px, `#E0CAA8` |
| Title | 16 pt SemiBold, `#3B2314`, left-aligned |

---

## Do Not Use

These effects **will not translate** to Java Swing:

| Avoid | Reason |
|---|---|
| Blurred box-shadows | Swing has no blur engine. Use 1 px solid borders. |
| Frosted glass / backdrop-filter | No blur-behind compositing. |
| CSS transitions / spring animations | State changes must be instant color swaps. |
| Multi-stop gradients (3+ colors) | `GradientPaint` supports exactly 2 colors. |
| Radial gradients | Stick to linear (left→right or top→bottom). |
| Text shadow / glow | Not in standard Swing text rendering. |
| Border-image / gradient borders | Solid-color borders only. |
| SVG / icon fonts | Use PNG `ImageIcon` in Swing. |
| Scroll-linked animations / parallax | Swing scroll is basic. |
| Complex clip-path shapes | Rounded rectangles (`RoundRectangle2D`) only. |
| Semi-transparency / alpha blending | Unreliable cross-platform. Prefer solid fills. |

**Do use:** Flat fills · 2-color linear gradients · Rounded rectangles · Solid 1–2 px borders · Instant hover/pressed color swaps · PNG images

---

## Content Fundamentals

**Tone:** Warm, friendly, direct. Coffee-shop approachable — not corporate.

**Casing rules:**
- Screen titles → Title Case ("Order Entry")
- Button labels → Title Case ("Add to Cart")
- Table headers → Title Case ("Order ID")
- Nav items → Title Case ("Dashboard")
- Form labels → Sentence case ("Total revenue today")

**Voice:** Second person where natural ("Your orders"). Keep labels brief — no full sentences on buttons.

**Numbers:** `$` prefix, comma separators (`$1,234.50`). Stat card values are the hero — large and prominent.

**No emoji. No decorative Unicode.**

---

## Visual Foundations

**Palette feel:** Warm earth tones. Specialty coffee signage — dark wood, cream paper, copper warmth. Nothing cold or blue.

**Backgrounds:** Solid fills only. Page: `#FFF8F0`. Cards: `#FFFFFF`. Sidebar: dark-brown gradient.

**Layout:** Fixed 240 px sidebar on left. Content fills remaining width. 32 px content padding. No floating elements.

**Cards:** White bg, 1 px `#E0CAA8` border, 12 px radius. Optional 4 px left accent strip for stat cards. No shadow (not achievable in Swing).

**Borders:** 1 px solid only. `#E0CAA8` for light contexts, `#D4B896` for inputs.

**Hover:** Instant color swap. Buttons lighten; nav items gain a subtle bg fill.

**Press:** Darker shade of base color. Instant.

**Imagery:** Circular product photos (120 px Ø). PNG. Placeholder: beige circle `#F5E6D3`.

**Icons:** Minimal — sidebar nav only. 20 × 20 px. Monochrome, matching text color. PNG for Swing.

**Radii:** 8 px (buttons, inputs) · 12 px (cards) · 9999 px (pills, circles).

**Animation:** None. All state changes are instant.

---

## Iconography

Café Lumière uses a **minimal, fixed icon set** — sidebar navigation only:

- Dashboard (grid icon)
- Orders (receipt icon)
- Inventory (package icon)
- Revenue (bar-chart icon)
- Logout (door/arrow icon)

**Format:** PNG, 20 × 20 px (40 × 40 px @2x for HiDPI). Single-color, matches nav text.

**No icon font. No SVG sprites. No emoji.**

In Swing: load via `ImageIcon` from bundled PNG resources.

> **Note:** This design system's web representation uses inline SVG stand-ins for these icons. When implementing in Swing, replace with PNG `ImageIcon` equivalents.

---

## File Index

| Path | Description |
|---|---|
| `readme.md` | This file — full design spec |
| `SKILL.md` | Agent skill file (Claude Code compatible) |
| `styles.css` | Global CSS entry (imports only) |
| `tokens/colors.css` | Color custom properties |
| `tokens/typography.css` | Font import + type tokens |
| `tokens/spacing.css` | Spacing, radius, layout tokens |
| **Guidelines (specimen cards)** | |
| `guidelines/colors/` | Brown scale, beige scale, semantic colors, chart palette |
| `guidelines/typography/` | Display/heading, body/labels, font weights |
| `guidelines/spacing/` | Spacing scale, border radii, gradient pairs |
| `guidelines/brand/` | Brand mark, Swing constraints |
| **Components** | |
| `components/core/Button` | Primary/secondary/danger button (KButton mapping) |
| `components/cards/StatCard` | Dashboard KPI card with accent strip |
| `components/feedback/Badge` | Warning/success/neutral pill badge |
| `components/inputs/InputField` | Labeled text input with error state |
| `components/navigation/SidebarNav` | Dark gradient sidebar with nav items |
| `components/drinks/DrinkCard` | Product card with circular image + add-to-cart |
| `components/data/DataTable` | Alternating-row data table |
| **UI Kit** | |
| `ui_kits/desktop_app/index.html` | Interactive 5-screen app (Login → Dashboard → Orders → Inventory → Revenue) |
