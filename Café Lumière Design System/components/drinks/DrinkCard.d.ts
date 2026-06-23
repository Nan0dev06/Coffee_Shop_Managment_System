/**
 * Drink product card for the Order Entry screen — circular image, name, price, and add-to-cart button.
 *
 * @startingPoint section="Components" subtitle="Order entry drink card with add-to-cart" viewport="220x320"
 */
export interface DrinkCardProps {
  /** Drink name */
  name: string;
  /** Formatted price (e.g. "$4.50") */
  price: string;
  /** Product image URL (circular) */
  imageUrl?: string;
  /** Single character or short text shown when no image */
  imagePlaceholder?: string;
  /** Called when Add to Cart is clicked */
  onAdd?: () => void;
  style?: React.CSSProperties;
}

export function DrinkCard(props: DrinkCardProps): JSX.Element;
