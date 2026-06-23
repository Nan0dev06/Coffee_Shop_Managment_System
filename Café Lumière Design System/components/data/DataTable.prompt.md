# DataTable

Simple alternating-row table for Inventory and Recent Orders. No pagination, no search — kept deliberately simple per the Swing app spec.

```jsx
<DataTable
  columns={[
    { key: 'id', label: 'Order ID', width: '100px' },
    { key: 'customer', label: 'Customer' },
    { key: 'total', label: 'Total', align: 'right' },
  ]}
  rows={[
    { id: '#001', customer: 'Alice', total: '$12.50' },
    { id: '#002', customer: 'Bob', total: '$8.00' },
  ]}
/>
```

Use `renderCell` for custom content like badges:

```jsx
renderCell={(row, col) =>
  col.key === 'stock' && row.stock < 10
    ? <Badge variant="warning">Low Stock</Badge>
    : row[col.key]
}
```
