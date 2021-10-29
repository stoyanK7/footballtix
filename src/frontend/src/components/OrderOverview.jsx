import '../css/components/OrderOverview.css';

import { useEffect, useState } from 'react';

import axios from 'axios';
import { useLocation } from 'react-router';

const OrderOverview = () => {
  const [match, setMatch] = useState({});
  const pathname = useLocation().pathname;
  const matchId = pathname.split("/")[2];

  useEffect(() => {
    axios.get(`/api/matches/${matchId}`)
      .then(res => {
        setMatch(res.data);
      });
  }, [matchId]);

  const date = new Date(match.startingDateTime);
  const matchDate = `${date.toLocaleDateString("en-US", { weekday: 'long' })}, ${date.getDay()} ${date.toLocaleString('default', { month: 'long' })} ${date.getFullYear()} ${date.getHours().toLocaleString('en-US', { minimumIntegerDigits: 2, useGrouping: false })}:${date.getMinutes().toLocaleString('en-US', { minimumIntegerDigits: 2, useGrouping: false })}`;

  const [fields, setFields] = useState({
    "fullName": "",
    "email": "",
    "mobilePhone": "",
    "address": "",
    "city": "",
    "country": "",
    "postcode": ""
  });

  const onChange = (e) => {
    setFields({
      ...fields,
      [e.target.name]: e.target.value
    });
  };

  return (
    <div className="OrderOverview">
      <section className="MatchOverviewSection">
        <span className="MatchOverviewSectionTitle">Match</span>
        <span className="MatchOverviewLeague">{match.league}</span>
        <div>
          <span className="MatchOverviewRival">{match.homeTeam}</span>
          &nbsp;v&nbsp;
          <span className="MatchOverviewRival">{match.awayTeam}</span>
        </div>
        <span className="MatchOverviewLocaion">{match.stadium}, {match.location}</span>
        <span className="MatchOverviewDate">{matchDate}</span>
      </section>
      <section className="MatchOverviewSection">
        <span className="MatchOverviewSectionTitle">Delivery</span>
        <p><span className="Bold">Electronic delivery</span> - your tickets will be emailed to you</p>
      </section>
      <section className="MatchOverviewSection">
        <span className="MatchOverviewSectionTitle">Price</span>
        <div className="MatchOverviewPricePerTicketWrapper">
          <img src="/img/ticket.png"/>
          <span className="MatchOverviewPricePerTicket">€{match.pricePerTicket}</span>
        </div>
      </section>
      <section className="MatchOverviewSection">
        <form >
          <input
            name="fullName"
            placeholder="Full name"
            type="text"
            onChange={onChange} />
          <input
            name="email"
            placeholder="Email"
            type="email"
            onChange={onChange} />
          <input
            name="mobilePhone"
            placeholder="Mobile phone"
            type="number"
            onChange={onChange} />
          <input
            name="address"
            placeholder="Address"
            type="text"
            onChange={onChange} />
          <input
            name="city"
            placeholder="Town/City"
            type="text"
            onChange={onChange} />
          <input
            name="country"
            placeholder="Country"
            type="text"
            onChange={onChange} />
          <input
            name="postcode"
            placeholder="Postcode"
            type="text"
            onChange={onChange} />

          <div className="FlexStart">
            <input type="checkbox" name="terms-and-conditions" id="terms-and-conditions" required />
            <label htmlFor="terms-and-conditions">I have read and agree to the<span className="Green">Terms and conditions</span></label>
          </div>
          <div className="FlexStart">
            <input type="checkbox" name="terms-and-conditions" id="terms-and-conditions" required />
            <label htmlFor="terms-and-conditions">I have read and understood the<span className="Green">Privacy policy</span></label>
          </div>
          <button className="MatchOverviewButton">
            MAKE PAYMENT
          </button>
        </form>
      </section >
    </div>
  );
};

export default OrderOverview;