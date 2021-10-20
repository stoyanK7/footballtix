import { useState } from 'react';
import axios from 'axios';
import Image from '../Image/Image.jsx';
import Input from '../Input/Input.jsx';
import '../css/Form.css';

const CreateMatch = () => {
  const [fields, setFields] = useState({
    homeTeam: "",
    awayTeam: "",
    startingDateTime: "",
    stadium: "",
    location: "",
    league: "",
    ticketsAvailable: 0,
    pricePerTicket: 0.0
  });

  const onChange = (e) => {
    setFields({
      ...fields,
      [e.target.name]: e.target.value
    });
  };

  const createMatch = (e) => {
    e.preventDefault();
    axios.post("/api/matches", {
      ...fields
    })
  };

  return (
    <div className="FormWrapper">
      <Image src="/img/plus.png" alt="Plus"/>
      <h1 className="FormTitle">Create match</h1>
      <form className="Form" onSubmit={createMatch}>
        <Input
          name="homeTeam"
          placeholder="Home team"
          type="text"
          onChange={onChange} />
        <Input
          name="awayTeam"
          placeholder="Away team"
          type="text"
          onChange={onChange} />
        <Input
          name="startingDateTime"
          placeholder="Starting date time"
          type="text"
          onChange={onChange} 
          onFocus={(e) => e.target.type = 'datetime-local'}
          onBlur={(e) => e.target.type = 'text'}/>
        <Input
          name="stadium"
          placeholder="Stadium"
          type="text"
          onChange={onChange} />
        <Input
          name="location"
          placeholder="City, Country"
          type="text"
          onChange={onChange} />
        <Input
          name="league"
          placeholder="League"
          type="text"
          onChange={onChange} />
        <Input
          name="ticketsAvailable"
          placeholder="Tickets available"
          type="number"
          onChange={onChange}
          min="0" />
        <Input
          name="pricePerTicket"
          placeholder="Price per ticket"
          type="number"
          onChange={onChange}
          min="0"
          max="10000" />
        <button type="submit" className="Button">Create</button>
      </form>
    </div>
  );
};

export default CreateMatch;