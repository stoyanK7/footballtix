import '../css/components/EditMatch.css';

import MessageBox from './MessageBox.jsx';
import axios from 'axios';
import { useEffect } from 'react';
import { useLocation } from 'react-router';
import { useState } from 'react';

const EditMatch = () => {
  const [match, setMatch] = useState({});
  const pathname = useLocation().pathname;
  const matchId = pathname.split("/")[2];
  const [messageBoxDisplay, setMessageBoxDisplay] = useState(false);
  const [messageBoxContent, setMessageBoxContent] = useState("");
  
  useEffect(() => {
    axios.get(`/api/matches/${matchId}`)
      .then(res => {
        setMatch(res.data);
      });
  }, [matchId]);


  const onChange = (e) => {
    setMatch({
      ...match,
      [e.target.name]: e.target.value
    });
  };

  const editMatch = (e) => {
    e.preventDefault();
    axios.put(`/api/matches/${matchId}`, {
      ...match
    })
    .then(res => {
      setMessageBoxContent(res.data);
      setMessageBoxDisplay(true);
    });
  };


  return (
    <div className="FormWrapper">
      <img src="/img/edit.png" alt="Pencil" />
      <h1 >Edit match</h1>
      <form  onSubmit={editMatch}>
        <input
          name="homeTeam"
          placeholder="Home team"
          type="text"
          value={match.homeTeam}
          onChange={onChange} />
        <input
          name="awayTeam"
          placeholder="Away team"
          type="text"
          value={match.awayTeam}
          onChange={onChange} />
        <input
          name="startingDateTime"
          placeholder="Starting date time"
          type="text"
          onChange={onChange}
          value={match.startingDateTime}
          onFocus={(e) => e.target.type = 'datetime-local'}
          onBlur={(e) => e.target.type = 'text'} />
        <input
          name="stadium"
          placeholder="Stadium"
          type="text"
          value={match.stadium}
          onChange={onChange} />
        <input
          name="location"
          placeholder="City, Country"
          type="text"
          value={match.location}
          onChange={onChange} />
        <input
          name="league"
          placeholder="League"
          type="text"
          value={match.league}
          onChange={onChange} />
        <input
          name="ticketsAvailable"
          placeholder="Tickets available"
          type="number"
          value={match.ticketsAvailable}
          onChange={onChange}
          min="0" />
        <input
          name="pricePerTicket"
          placeholder="Price per ticket"
          type="number"
          value={match.pricePerTicket}
          onChange={onChange}
          min="0"
          max="10000" />
          {messageBoxDisplay && 
            <MessageBox content={messageBoxContent}/>
          }
        <button type="submit">Confirm</button>
      </form>
    </div>
  );
};

export default EditMatch;