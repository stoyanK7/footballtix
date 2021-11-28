import '../../css/shared/TicketsLeft.css';

const TicketsLeft = ({ ticketsAvailable }) => {
  return (
    <div className='tickets-left'>
      <span
        className={`ticket-amount ${(ticketsAvailable === 0 && 'ticket-amount-red')
          || ((ticketsAvailable > 0 && ticketsAvailable <= 50) && 'ticket-amount-orange')
          || ((ticketsAvailable > 50) && 'ticket-amount-green')}`
        }>
        {ticketsAvailable}</span>
      <span className='ticket-amount-desc'>&nbsp; Tickets available</span>
    </div>
  );
};

export default TicketsLeft;
