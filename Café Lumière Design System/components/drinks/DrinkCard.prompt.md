# DrinkCard

Product card for the Order Entry screen. Shows a circular product image (or placeholder letter), drink name, price, and a full-width primary "Add to Cart" button.

```jsx
<DrinkCard
  name="Americano"
  price="$4.50"
  imageUrl="/images/americano.png"
  onAdd={() => addToCart('americano')}
/>

{/* Placeholder without image */}
<DrinkCard name="Latte" price="$5.00" />
```
