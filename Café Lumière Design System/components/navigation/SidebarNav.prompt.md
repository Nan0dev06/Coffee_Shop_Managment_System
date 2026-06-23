# SidebarNav

Dark-brown gradient sidebar with brand name, navigation items, and a bottom-pinned logout.
Maps to a KGradientPanel in Swing with JButtons styled as nav items.

```jsx
<SidebarNav
  brandName="Café Lumière"
  activeId="dashboard"
  onSelect={(id) => setScreen(id)}
  items={[
    { id: 'dashboard', label: 'Dashboard', icon: '⊞' },
    { id: 'orders', label: 'Orders', icon: '☰' },
    { id: 'inventory', label: 'Inventory', icon: '▦' },
    { id: 'revenue', label: 'Revenue', icon: '◔' },
    { id: 'logout', label: 'Log Out', icon: '⇥' },
  ]}
/>
```
