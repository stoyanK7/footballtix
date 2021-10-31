import Loading from '../shared/Loading';
import MatchList from '../shared/MatchList';
import MessageBox from '../shared/MessageBox';
import useGET from '../../hooks/useGET';
import useMessage from '../../hooks/useMessage';

const Home = () => {
  const { data: upcomingMatches, isPending, error } = useGET('/api/matches/upcoming');

  const message = useMessage();

  return (
    <>
      {isPending && <Loading />}
      {error && <MessageBox content={error} type='error' />}
      {upcomingMatches &&
        <>
          <MatchList matches={upcomingMatches} />
          {message && <MessageBox content={message} type='success' />}
        </>
      }
    </>
  );
};

export default Home;
