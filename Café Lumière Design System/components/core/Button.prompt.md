# Button

Action button mapping to KControls KButton. Three variants: primary (brown gradient), secondary (beige flat), danger (terracotta red). Three sizes: sm, md, lg.

```jsx
<Button variant="primary" size="md" onClick={handleClick}>
  Add to Cart
</Button>

<Button variant="secondary">Cancel</Button>

<Button variant="danger">Restock Item</Button>

<Button variant="primary" fullWidth>Submit Order</Button>

<Button variant="primary" disabled>Processing...</Button>
```

**KButton mapping:** `variant="primary"` → startColor=#5C3A27, endColor=#6F4E37, hoverColor=#7D5A43, pressedColor=#4E2E1E
