import React from 'react';
import { render } from 'enzyme';
import App from './App';

it('renders welcome message', () => {
  const wrapper = render(<App />);
  expect(wrapper.find('h1')).toHaveLength(1);
});
