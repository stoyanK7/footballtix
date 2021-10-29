import '../css/components/MatchOverview.css';

import React, { useEffect, useState } from "react";

import { Link } from "react-router-dom";
import axios from "axios";
import { useLocation } from "react-router";

const MatchOverview = () => {
  const [match, setMatch] = useState({});
  const matchId = useLocation().pathname.substring(useLocation().pathname.lastIndexOf('/') + 1);

  useEffect(() => {
    axios.get(`/api/matches/${matchId}`)
      .then(res => {
        setMatch(res.data);
      });
  }, [matchId]);

  // 'dddd, dd mmmm yyyy HH:MM'

  const date = new Date(match.startingDateTime);
  const matchDate = `${date.toLocaleDateString("en-US", { weekday: 'long' })}, ${date.getDay()} ${date.toLocaleString('default', { month: 'long' })} ${date.getFullYear()} ${date.getHours().toLocaleString('en-US', { minimumIntegerDigits: 2, useGrouping: false })}:${date.getMinutes().toLocaleString('en-US', { minimumIntegerDigits: 2, useGrouping: false })}`;

  const confirmDeletion = () => {
    const confirmation = window.confirm("Are you sure you want to delete this match?");
    console.log(confirmation);
    if(confirmation) {
      axios.delete(`/api/matches/${matchId}`)
      .then(res => {
      });
    } 
  };

  return (
    <div className="MatchOverview">
      <img src="/img/stadium.png" />
      <span className="MatchOverviewLeague">{match.league}</span>
      <div>
        <span className="MatchOverviewRival">{match.homeTeam}</span>
        &nbsp;v&nbsp;
        <span className="MatchOverviewRival">{match.awayTeam}</span>
      </div>
      <span className="MatchOverviewLocaion">{match.stadium}, {match.location}</span>
      <span className="MatchOverviewDate">{matchDate}</span>
      <div>
        <span className="MatchOverviewTicketAmount">{match.ticketsAvailable}</span>
        <span className="MatchOverviewTicketAmountDesc">&nbsp; Tickets available</span>
      </div>
      <div className="MatchOverviewPricePerTicketWrapper">
        <img src="/img/ticket.png" />
        <span className="MatchOverviewPricePerTicket">€{match.pricePerTicket}</span>
      </div>
      <Link to={`/matches/${matchId}/order`}>
        <button className="MatchOverviewButton">
          BUY
        </button>
      </Link>
      <Link to={`/matches/${matchId}/edit`}>
        <button className="EditMatch">
          EDIT MATCH
        </button>
      </Link>
      <button className="DeleteMatch" onClick={confirmDeletion}>
        DELETE MATCH
      </button>
    </div>
  );
};

export default MatchOverview;