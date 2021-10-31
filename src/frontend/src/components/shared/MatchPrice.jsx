import '../../css/static/MatchPrice.css';

const MatchPrice = ({ pricePerTicket }) => {
  return (
    <div className='match-price'>
      <img src='/img/ticket.png' alt='Ticket' />
      <span className='price-per-ticket'>€{pricePerTicket}</span>
    </div>
  );
}

export default MatchPrice;
