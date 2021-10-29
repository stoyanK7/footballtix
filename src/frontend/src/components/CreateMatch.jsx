import axios from 'axios';
import { useState } from 'react';

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
      <img src="/img/plus.png" alt="Plus"/>
      <h1 className="FormTitle">Create match</h1>
      <form onSubmit={createMatch}>
        <input
          name="homeTeam"
          placeholder="Home team"
          type="text"
          onChange={onChange} />
        <input
          name="awayTeam"
          placeholder="Away team"
          type="text"
          onChange={onChange} />
        <input
          name="startingDateTime"
          placeholder="Starting date time"
          type="text"
          onChange={onChange} 
          onFocus={(e) => e.target.type = 'datetime-local'}
          onBlur={(e) => e.target.type = 'text'}/>
        <input
          name="stadium"
          placeholder="Stadium"
          type="text"
          onChange={onChange} />
        <input
          name="location"
          placeholder="City, Country"
          type="text"
          onChange={onChange} />
        <input
          name="league"
          placeholder="League"
          type="text"
          onChange={onChange} />
        <input
          name="ticketsAvailable"
          placeholder="Tickets available"
          type="number"
          onChange={onChange}
          min="0" />
        <input
          name="pricePerTicket"
          placeholder="Price per ticket"
          type="number"
          onChange={onChange}
          min="0"
          max="10000" />
        <button type="submit">Create</button>
      </form>
    </div>
  );
};

export default CreateMatch;