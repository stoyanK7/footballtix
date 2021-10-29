import '../css/components/Main.css';

import GreenLine from './GreenLine';
import { createElement } from 'react';

const Main = ({ content, component }) => {
  return (
    <main>
      <GreenLine content={content} />
      {createElement(component)}
    </main>
  );
};

export default Main;
