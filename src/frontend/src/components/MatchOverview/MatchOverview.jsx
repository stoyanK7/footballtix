import React, { useEffect, useState } from "react";
import axios from "axios";
import { useLocation } from "react-router";
import './MatchOverview.css';
import Image from "../Image/Image";
import Logo from "../Logo/Logo";
import { Link } from "react-router-dom";

const MatchOverview = () => {
  const [match, setMatch] = useState({});
  const matchId = useLocation().pathname.substring(useLocation().pathname.lastIndexOf('/') + 1);

  useEffect(() => {
    axios.get(`/api/matches/${matchId}`)
      .then(res => {
        setMatch(res.data);
      });
  }, []);

  // 'dddd, dd mmmm yyyy HH:MM'

  const date = new Date(match.startingDateTime);
  const matchDate = `${date.toLocaleDateString("en-US", { weekday: 'long' })}, ${date.getDay()} ${date.toLocaleString('default', { month: 'long' })} ${date.getFullYear()} ${date.getHours().toLocaleString('en-US', {minimumIntegerDigits: 2, useGrouping:false})}:${date.getMinutes().toLocaleString('en-US', {minimumIntegerDigits: 2, useGrouping:false})}`;

  return (
    <div className="MatchOverview">
      <Image src="/img/stadium.png" />
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
        <Logo/>
        <span className="MatchOverviewPricePerTicket">€{match.pricePerTicket}</span>
      </div>
      <Link to={`/matches/${matchId}/order`}>
        <button className="Button MatchOverviewButton">
          BUY
        </button>
      </Link>
    </div>
  );
};

export default MatchOverview;