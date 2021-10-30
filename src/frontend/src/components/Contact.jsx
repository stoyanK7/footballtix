import '../css/components/Contact.css'

const Contact = () => {
  return (
    <div className='contact'>
      <a href='tel:+359878890852'>
        <div>
          <p>
            Call us at <span>+359 878 890 852</span> 
          </p>
          <img src='/img/phone-call.png' alt='Phone' />
        </div>
      </a>
      <a href='mailto:office@footballtix.com'>
        <div>
          <p>
            Email us at <span>office@footballtix.com</span>
          </p>
          <img src='/img/email.png' alt='Mail' />
        </div>
      </a>
    </div>
  )
};

export default Contact;
