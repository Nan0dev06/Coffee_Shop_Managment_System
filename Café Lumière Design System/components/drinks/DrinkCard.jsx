import React from 'react';

export function DrinkCard({
  name,
  price,
  imageUrl,
  imagePlaceholder,
  onAdd,
  style: styleProp,
  ...props
}) {
  const [hover, setHover] = React.useState(false);
  const [pressed, setPressed] = React.useState(false);

  const cardStyle = {
    fontFamily: 'var(--font-family)',
    background: 'var(--surface-card)',
    border: '1px solid var(--border-light)',
    borderRadius: 'var(--radius-lg)',
    padding: 'var(--space-16)',
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    gap: 'var(--space-8)',
    width: '180px',
    boxSizing: 'border-box',
    ...styleProp,
  };

  const imgWrap = {
    width: '120px',
    height: '120px',
    borderRadius: 'var(--radius-round)',
    background: 'var(--beige-100)',
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
    overflow: 'hidden',
    flexShrink: 0,
  };

  const imgStyle = {
    width: '100%',
    height: '100%',
    objectFit: 'cover',
    borderRadius: 'var(--radius-round)',
  };

  const placeholderStyle = {
    fontSize: '36px',
    fontWeight: 'var(--font-weight-bold)',
    color: 'var(--brown-400)',
  };

  const nameStyle = {
    fontSize: 'var(--font-size-subheading)',
    fontWeight: 'var(--font-weight-semibold)',
    color: 'var(--text-primary)',
    margin: 0,
    textAlign: 'center',
    lineHeight: 'var(--line-height-tight)',
  };

  const priceStyle = {
    fontSize: 'var(--font-size-label)',
    fontWeight: 'var(--font-weight-regular)',
    color: 'var(--text-secondary)',
    margin: 0,
  };

  const btnBg = pressed ? 'var(--btn-primary-pressed)' : hover ? 'var(--btn-primary-hover)'
    : 'linear-gradient(to right, var(--btn-primary-start), var(--btn-primary-end))';

  const btnStyle = {
    fontFamily: 'var(--font-family)',
    fontSize: 'var(--font-size-label)',
    fontWeight: 'var(--font-weight-semibold)',
    color: 'var(--btn-primary-text)',
    background: btnBg,
    border: 'none',
    borderRadius: 'var(--radius-md)',
    padding: '8px 0',
    cursor: 'pointer',
    width: '100%',
    marginTop: 'var(--space-4)',
  };

  return React.createElement('div', { style: cardStyle, ...props },
    React.createElement('div', { style: imgWrap },
      imageUrl
        ? React.createElement('img', { src: imageUrl, alt: name, style: imgStyle })
        : React.createElement('span', { style: placeholderStyle }, imagePlaceholder || (name ? name[0] : '?'))
    ),
    React.createElement('p', { style: nameStyle }, name),
    React.createElement('p', { style: priceStyle }, price),
    React.createElement('button', {
      style: btnStyle,
      onClick: onAdd,
      onMouseEnter: () => setHover(true),
      onMouseLeave: () => { setHover(false); setPressed(false); },
      onMouseDown: () => setPressed(true),
      onMouseUp: () => setPressed(false),
    }, 'Add to Cart')
  );
}
