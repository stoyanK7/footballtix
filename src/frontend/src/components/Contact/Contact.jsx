import './Contact.css'

const Contact = () => {
  return (
    <div className="Contact">
      <a href="tel:123456789">
        <section>
          Call us
          <img src="/img/phone-call.png" alt="" />
        </section>
      </a>
      <a href="mailto:office@footballtix.com">
        <section>
          Email us
          <img src="/img/email.png" alt="" />
        </section>
      </a>
    </div>
  )
};

export default Contact;