import axios from 'axios';
import { Link } from "react-router-dom";
import { useEffect, useState } from 'react';
import MatchCard from '../MatchCard/MatchCard';
import './UpcomingMatches.css';

const UpcomingMatches = () => {
  const [upcomingMathes, setUpcomingMatches] = useState([]);

  useEffect(() => {
    axios.get("/api/matches/upcoming")
      .then(res => {
        setUpcomingMatches(res.data);
      });
  }, []);

  return (
    <div className="UpcomingMatches">
      {upcomingMathes.map(match => (
        <Link to={"/matches/" + match.id}>
          <MatchCard {...match} />
        </Link>
      ))}
    </div>
  );
};

export default UpcomingMatches;
