import '../../css/shared/TicketInfo.css';

const TicketInfo = ({ pricePerTicket }) => {
  return (
    <div className='ticket-info'>
      <img src='/img/ticket.webp' alt='Ticket' />
      <span className='price-per-ticket'>€{pricePerTicket}</span>
    </div>
  );
}

export default TicketInfo;
