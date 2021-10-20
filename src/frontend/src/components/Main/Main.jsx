import { createElement } from 'react';
import GreenLine from '../GreenLine/GreenLine';
import './Main.css';

const Main = ({content, component}) => {
  return (
    <main className="Main">
      <GreenLine content={content}/>
      {createElement(component)}
    </main>
  );
};

export default Main;
