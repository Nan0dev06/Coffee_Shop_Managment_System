/**
 * Sidebar navigation panel with dark brown gradient background.
 * Shows brand name, nav items with active state, and a bottom-pinned logout.
 *
 * @startingPoint section="Components" subtitle="Dark sidebar with brand, nav items, and logout" viewport="260x400"
 */
export interface NavItem {
  id: string;
  label: string;
  /** Icon character or emoji stand-in (replaced with PNG ImageIcon in Swing) */
  icon?: string;
}

export interface SidebarNavProps {
  /** Navigation items — item with id="logout" pins to bottom */
  items?: NavItem[];
  /** Currently active item id */
  activeId?: string;
  /** Called with item id on click */
  onSelect?: (id: string) => void;
  /** Brand name displayed at top */
  brandName?: string;
  style?: React.CSSProperties;
}

export function SidebarNav(props: SidebarNavProps): JSX.Element;
