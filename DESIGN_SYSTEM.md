# Café Lumière — Design System

Visual language for the Java Swing desktop application. All tokens are already translated into `Theme.java` — use that file as the single source of truth in code. This document is the reference spec.

---

## Color Tokens

### Brown Scale (Primary)

| Token | Hex | Usage |
|---|---|---|
| `brown-900` | `#3B2314` | Primary text on light backgrounds |
| `brown-800` | `#4E2E1E` | Sidebar bg, gradient dark end, pressed button |
| `brown-700` | `#5C3A27` | Primary button gradient start |
| `brown-600` | `#6F4E37` | Brand base brown, primary button gradient end |
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

**Font:** Nunito — TTF files bundled under `src/main/resources/fonts/`, loaded via `Font.createFont()`.
**Fallback:** `SansSerif`

| Role | Swing pt | Weight |
|---|---|---|
| Display | 28 pt | Bold 700 |
| Heading | 22 pt | Bold 700 |
| Subheading | 16 pt | SemiBold 600 |
| Body | 14 pt | Regular 400 |
| Label | 12 pt | Medium 500 |
| Caption | 11 pt | Regular 400 |

**Text colors:**
- On light backgrounds → `#3B2314`
- On dark backgrounds (sidebar, buttons) → `#FFF8F0`
- Muted / secondary → `#8B6B53`

---

## Spacing Scale

| Token | Value | Usage |
|---|---|---|
| `space-4` | 4 px | Tight gaps |
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
| Label style | 14 pt Medium, `#E0CAA8` |
| Active state | bg `#6F4E37`, text `#FFFFFF`, radius 8 px |
| Hover state | bg `#5C3A27`, text `#FFF8F0` |
| Logout | Bottom-pinned, warning color on hover (`#B7472A`) |

### Stat Card

| Property | Value |
|---|---|
| Background | `#FFFFFF` |
| Border | 1 px solid `#E0CAA8` |
| Border radius | 12 px |
| Padding | 24 px |
| Left accent | 4 px wide, `#6F4E37` → `#8B6B53` gradient |
| Label | 12 pt Medium, `#8B6B53` |
| Value | 22 pt Bold, `#3B2314` |

### Button (KButton)

**Primary**

| Property | Value |
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

| Property | Value |
|---|---|
| `kStartColor` / `kEndColor` | `#EDD9BC` |
| `kHoverColor` | `#E0CAA8` |
| `kPressedColor` | `#D4B896` |
| `foreground` | `#3B2314` |

**Danger**

| Property | Value |
|---|---|
| `kStartColor` / `kEndColor` | `#B7472A` |
| `kHoverColor` | `#9A3B22` |
| `kPressedColor` | `#7A2E1A` |
| `foreground` | `#FFFFFF` |

### Drink Card (Order Entry)

| Property | Value |
|---|---|
| Background | `#FFFFFF` |
| Border | 1 px solid `#E0CAA8` |
| Border radius | 12 px |
| Padding | 16 px |
| Image | 120 × 120 px circle, loaded from `resources/images/` |
| Title | 16 pt SemiBold, `#3B2314`, centered |
| Price | 14 pt Regular, `#8B6B53`, centered |
| Add button | Full-width Primary KButton — "Add to Cart" |

### Table / List Row

| Property | Value |
|---|---|
| Header text | 12 pt SemiBold, `#3B2314` |
| Body rows | Alternating `#FFFFFF` / `#FFF8F0` |
| Row height | 44 px minimum |
| Cell padding | 12 px h · 8 px v |
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
| Bar colors | See Chart Bar Colors above |
| Chart bg | `#FFFFFF` |
| Axis labels | 12 pt Regular, `#3B2314` |
| Gridlines | 1 px, `#E0CAA8` |

---

## Swing Constraints — What NOT to Use

These effects do not translate to Java Swing:

| Avoid | Use instead |
|---|---|
| Blurred box-shadows | 1 px solid border (`#E0CAA8`) |
| Frosted glass / backdrop-filter | Solid fill |
| CSS transitions / animations | Instant color swaps on hover/press |
| Multi-stop gradients (3+ colors) | `GradientPaint` supports exactly 2 colors |
| Radial gradients | Linear only (left→right or top→bottom) |
| Text shadow / glow | Plain text rendering |
| SVG / icon fonts | PNG `ImageIcon` |
| Semi-transparency / alpha blending | Solid fills only |

**Do use:** Flat fills · 2-color linear gradients · Rounded rectangles (`fillRoundRect`) · Solid 1–2 px borders · Instant hover/pressed color swaps · PNG images

---

## Layout

- Fixed 240 px sidebar on the left
- Content fills remaining width
- 32 px content padding on all sides
- `CardLayout` for screen switching (no floating panels)
- `JScrollPane` wrapping each content page (vertical scroll only)

---

## Content Rules

**Casing:**
- Screen titles → Title Case ("Order Entry")
- Button labels → Title Case ("Add to Cart")
- Table headers → Title Case ("Order ID")
- Nav items → Title Case ("Dashboard")
- Form labels → Sentence case ("Total revenue today")

**Numbers:** `$` prefix, two decimal places (`$12.50`). Stat card values are the hero — large and prominent.

No emoji. No decorative Unicode.
