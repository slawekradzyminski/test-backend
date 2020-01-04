import { configure } from 'enzyme';
import Adapter from 'enzyme-adapter-react-16';
import React from "react"
import 'jest-enzyme';

React.useLayoutEffect = React.useEffect
configure({ adapter: new Adapter() });
