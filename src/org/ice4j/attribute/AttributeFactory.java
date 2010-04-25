/*
 * ice4j, the OpenSource Java Solution for NAT and Firewall Traversal.
 * Maintained by the SIP Communicator community (http://sip-communicator.org).
 *
 * Distributable under LGPL license. See terms of license at gnu.org.
 */
package org.ice4j.attribute;

import org.ice4j.*;

/**
 * This class  provides factory methods to allow an application to create
 * STUN / TURN / ICE Attributes from a particular implementation.
 *
 * @author Emil Ivov
 * @author Sebastien Vincent
 * @author Namal Senarathne
 */
public class AttributeFactory
{
    private AttributeFactory()
    {
    }

//------------------------------------ CHANGE REQUEST --------------------------

    /**
     * Creates a ChangeRequestAttribute with "false" values for the changeIP and
     * changePort flags.
     * @return the newly created ChangeRequestAttribute.
     */
    public static ChangeRequestAttribute
                    createChangeRequestAttribute()
    {
        return createChangeRequestAttribute(false, false);
    };

    /**
     * Creates a ChangeRequestAttribute with the specified flag values.
     * @param changeIP   the value of the changeIP flag.
     * @param changePort the value of the changePort flag.
     * @return the newly created ChangeRequestAttribute.
     */
    public static ChangeRequestAttribute
                    createChangeRequestAttribute(boolean changeIP,
                                                 boolean changePort)
    {
        ChangeRequestAttribute attribute = new ChangeRequestAttribute();

        attribute.setChangeIpFlag(changeIP);
        attribute.setChangePortFlag(changePort);

        return attribute;
    };

//------------------------------------ CHANGED ADDRESS -------------------------

    /**
     * Creates a changedAddressAttribute of the specified type and with the
     * specified address and port
     * @param address the address value of the address attribute
     * @return the newly created address attribute.
     */
    public static ChangedAddressAttribute
                                  createChangedAddressAttribute(TransportAddress address)
    {
        ChangedAddressAttribute attribute= new ChangedAddressAttribute();

        attribute.setAddress(address);

        return attribute;

    };

//------------------------------------ ERROR CODE ------------------------------
    /**
     * Creates an ErrorCodeAttribute with the specified error class and number
     * and a default reason phrase.
     * @param errorClass a valid error class.
     * @param errorNumber a valid error number.
     * @return the newly created attribute.
     * @throws StunException if the error class or number have invalid values
     * according to rfc3489.
     */
    public static ErrorCodeAttribute createErrorCodeAttribute(
                        byte errorClass,
                        byte errorNumber
                        )
        throws StunException
    {
        return createErrorCodeAttribute(errorClass, errorNumber, null);
    };

    /**
     * Creates an ErrorCodeAttribute with the specified error class, number and
     * reason phrase.
     * @param errorClass a valid error class.
     * @param errorNumber a valid error number.
     * @param reasonPhrase a human readable reason phrase. A null reason phrase
     *                     would be replaced (if possible) by a default one
     *                     as defined byte the rfc3489.
     * @return the newly created attribute.
     * @throws StunException if the error class or number have invalid values
     * according to rfc3489.
     */
    public static ErrorCodeAttribute createErrorCodeAttribute(
                        byte errorClass,
                        byte errorNumber,
                        String reasonPhrase
                        )
        throws StunException
    {
        ErrorCodeAttribute attribute = new ErrorCodeAttribute();

        attribute.setErrorClass(errorClass);
        attribute.setErrorNumber(errorNumber);

        attribute.setReasonPhrase(reasonPhrase==null?
            ErrorCodeAttribute.getDefaultReasonPhrase(attribute.getErrorCode())
            :reasonPhrase);

        return attribute;
    };

    /**
     * Creates an ErrorCodeAttribute with the specified error code and a default
     * reason phrase.
     * @param errorCode a valid error code.
     * @return the newly created attribute.
     * @throws StunException if errorCode is not a valid error code as defined
     * by rfc3489
     */
    public static ErrorCodeAttribute createErrorCodeAttribute(char errorCode)
        throws StunException
    {
        return createErrorCodeAttribute(errorCode, null);
    };

    /**
     * Creates an ErrorCodeAttribute with the specified error code and reason
     * phrase.
     * @param errorCode a valid error code.
     * @param reasonPhrase a human readable reason phrase. A null reason phrase
     *                     would be replaced (if possible) by a default one
     *                     as defined byte the rfc3489.

     * @return the newly created attribute.
     * @throws StunException if errorCode is not a valid error code as defined
     * by rfc3489
     */
    public static ErrorCodeAttribute createErrorCodeAttribute(
                                        char errorCode,
                                        String reasonPhrase)
        throws StunException
    {
        ErrorCodeAttribute attribute = new ErrorCodeAttribute();

        attribute.setErrorCode(errorCode);
        attribute.setReasonPhrase(reasonPhrase==null?
            ErrorCodeAttribute.getDefaultReasonPhrase(attribute.getErrorCode())
            :reasonPhrase);


        return attribute;
    };

//------------------------------------ MAPPED ADDRESS --------------------------

    /**
     * Creates a MappedAddressAttribute of the specified type and with the
     * specified address and port
     * @param address the address value of the address attribute
     * @return the newly created address attribute.
     */
    public static MappedAddressAttribute createMappedAddressAttribute(
                                                TransportAddress address)
    {
        MappedAddressAttribute attribute = new MappedAddressAttribute();

        attribute.setAddress(address);

        return attribute;

    };

//------------------------------------ REFLECTED FROM --------------------------

    /**
     * Creates a ReflectedFromAddressAttribute of the specified type and with
     * the specified address and port
     * @param address the address value of the address attribute
     * @return the newly created address attribute.
     */
    public static ReflectedFromAttribute createReflectedFromAttribute(
                                                TransportAddress address)
    {
        ReflectedFromAttribute attribute = new ReflectedFromAttribute();

        attribute.setAddress(address);

        return attribute;

    };

//------------------------------------ RESPONSE ADRESS -------------------------
    /**
     * Creates a ResponseFromAddressAttribute of the specified type and with
     * the specified address and port
     * @param address the address value of the address attribute
     * @return the newly created address attribute.
     */
    public static ResponseAddressAttribute createResponseAddressAttribute(
                                                    TransportAddress address)
    {
        ResponseAddressAttribute attribute = new ResponseAddressAttribute();

        attribute.setAddress(address);

        return attribute;

    };

//------------------------------------ SOURCE ADDRESS --------------------------
    /**
     * Creates a SourceFromAddressAttribute of the specified type and with
     * the specified address and port
     * @param address the address value of the address attribute
     * @return the newly created address attribute.
     */
    public static SourceAddressAttribute createSourceAddressAttribute(
                                               TransportAddress address)
    {
        SourceAddressAttribute attribute = new SourceAddressAttribute();

        attribute.setAddress(address);

        return attribute;

    };

//------------------------------------ UNKNOWN ATTRIBUTES ----------------------
    /**
     * Creates an empty UnknownAttributesAttribute.
     * @return the newly created UnknownAttributesAttribute
     */
    public static UnknownAttributesAttribute createUnknownAttributesAttribute()
    {
        UnknownAttributesAttribute attribute = new UnknownAttributesAttribute();

        return attribute;
    };

/* ------------------------------------ XOR-RELAYED-ADRESS attribute ------------------------ */
    /**
     * Creates a XorRelayedAddressAttribute of the specified type and with
     * the specified address and port.
     *
     * @param address the address value of the address attribute
     * @param tranID the ID of the transaction that we will be using for the XOR
     * mask.
     *
     * @return the newly created address attribute.
     */
    public static XorRelayedAddressAttribute createXorRelayedAddressAttribute(
                                                    TransportAddress address,
                                                    byte[]           tranID)
    {
        XorRelayedAddressAttribute attribute = new XorRelayedAddressAttribute();

        //TODO (Emil): shouldn't we be XORing the address before setting it?
        attribute.setAddress(address, tranID);
        return attribute;
    }

/* ------------------------------------ XOR-PEER-ADRESS attribute ------------------------ */
    /**
     * Creates a XorPeerAddressAttribute of the specified type and with
     * the specified address and port
     *
     * @param address the address value of the address attribute
     * @param tranID the ID of the transaction that we will be using for the XOR
     * mask.
     * @return the newly created address attribute.
     */
    public static XorPeerAddressAttribute createXorPeerAddressAttribute(
                                                       TransportAddress address,
                                                       byte[]           tranID)
    {
        XorPeerAddressAttribute attribute = new XorPeerAddressAttribute();

        //TODO (Emil): shouldn't we be XORing the address before setting it?
        attribute.setAddress(address, tranID);
        return attribute;
    }

//------------------------------------ XOR MAPPED ADDRESS --------------------------
    /**
     * Creates a XorMappedAddressAttribute for the specified <tt>address</tt>.
     *
     * @param address the address value of the address attribute
     * @param tranID the ID of the transaction that we will be using for the XOR
     * mask.
     *
     * @return the newly created xor address attribute.
     */
    public static XorMappedAddressAttribute createXorMappedAddressAttribute(
                                                TransportAddress address,
                                                byte[]           tranID)
    {
        XorMappedAddressAttribute attribute = new XorMappedAddressAttribute();

        attribute.setAddress(address, tranID);

        return attribute;

    };

/* --------------------------------- USERNAME attribute ----------------------------- */
    /**
     * Create a UsernameAttribute.
     * @param username username value
     * @return newly created UsernameAttribute
     */
    public static UsernameAttribute createUsernameAttribute(byte username[])
    {
      UsernameAttribute attribute = new UsernameAttribute();

      attribute.setUsername(username);
      return attribute;
    }

/* --------------------------------- CHANNEL-NUMBER attribute ----------------------------- */
    /**
     * Create a ChannelNumberAttribute.
     * @param channelNumber channel number
     * @return newly created ChannelNumberAttribute
     */
    public static ChannelNumberAttribute createChannelNumberAttribute(char channelNumber)
    {
      ChannelNumberAttribute attribute = new ChannelNumberAttribute();

      attribute.setChannelNumber(channelNumber);
      return attribute;
    }

/* --------------------------------- REALM attribute ----------------------------- */
    /**
     * Create a RealmAttribute.
     * @param realm realm value
     * @return newly created RealmAttribute
     */
    public static RealmAttribute createRealmAttribute(byte realm[])
    {
      RealmAttribute attribute = new RealmAttribute();

      attribute.setRealm(realm);
      return attribute;
    }

/* --------------------------------- NONCE attribute ----------------------------- */
    /**
     * Create a NonceAttribute.
     * @param nonce nonce value
     * @return newly created NonceAttribute
     */
    public static NonceAttribute createNonceAttribute(byte nonce[])
    {
      NonceAttribute attribute = new NonceAttribute();

      attribute.setNonce(nonce);
      return attribute;
    }

/* --------------------------------- SOFTWARE attribute ----------------------------- */
    /**
     * Create a SoftwareAttribute.
     * @param software software value
     * @return newly created SoftwareAttribute
     */
    public static SoftwareAttribute createSoftwareAttribute(byte software[])
    {
      SoftwareAttribute attribute = new SoftwareAttribute();

      attribute.setSoftware(software);
      return attribute;
    }


/* --------------------------------- EVEN-PORT attribute ---------------- */
    /**
     * Create a EventAttribute.
     * @param rFlag R flag
     * @return the newly created EventPortAttribute
     */
    public static EvenPortAttribute createEvenPortAttribute(boolean rFlag)
    {
      EvenPortAttribute attribute = new EvenPortAttribute();

      attribute.setRFlag(rFlag);
      return attribute;
    }

/* --------------------------------- LIFETIME attribute --------------------------- */
    /**
     * Create a LifetimeAttribute.
     * @param lifetime lifetime value
     * @return newly created LifetimeAttribute
     */
    public static LifetimeAttribute createLifetimeAttribute(int lifetime)
    {
      LifetimeAttribute attribute = new LifetimeAttribute();

      attribute.setLifetime(lifetime);
      return attribute;
    }

/* --------------------------------- REQUESTED-TRANSPORT attribute ---------------- */

    /**
     * Create a RequestedTransportAttribute.
     * @param protocol transport protocol requested
     * @return newly created RequestedTransportAttribute
     */
    public static RequestedTransportAttribute createRequestedTransportAttribute(byte protocol)
    {
      RequestedTransportAttribute attribute = new RequestedTransportAttribute();

      attribute.setRequestedTransport(protocol);
      return attribute;
    }

/* -------------------------------- RESERVATION-TOKEN attribute -------------------- */
    /**
     * Create a ReservationTokenAttribute.
     * @param token the token
     * @return newly created RequestedTransportAttribute
     */
    public static ReservationTokenAttribute createReservationTokenAttribute(byte token[])
    {
      ReservationTokenAttribute attribute = new ReservationTokenAttribute();

      attribute.setReservationToken(token);
      return attribute;
    }

/* -------------------------------- DATA attribute --------------------------------- */
    /**
     * Create a DataAtttribute.
     * @param data the data
     * @return newly created DataAttribute
     */
    public static DataAttribute createDataAttribute(byte data[])
    {
      DataAttribute attribute = new DataAttribute();

      attribute.setData(data);
      return attribute;
    }

//-------------------------------- ICE-CONTROLLED ATTRIBUTE -----------------------
    /**
     * Creates an IceControlledAttribute object with the specified tie-breaker value
     *
     * @param tieBreaker  the tie-breaker value to be used
     * @return  the created IceControlledAttribute
     * @throws  StunException if the value in tie-breaker is not valid
     */
    public static IceControlledAttribute createIceControlledAttribute(long tieBreaker)
      throws StunException
    {
      // generating the network ordered byte stream of the tieBreaker long variable
      byte[] tieBreakerBytes = new byte[8];
      tieBreakerBytes[0] = (byte)(tieBreaker>>56);
      tieBreakerBytes[1] = (byte)((tieBreaker & 0x00FF000000000000L) >> 48);
      tieBreakerBytes[2] = (byte)((tieBreaker & 0x0000FF0000000000L) >> 40);
      tieBreakerBytes[3] = (byte)((tieBreaker & 0x000000FF00000000L) >> 32);
      tieBreakerBytes[4] = (byte)((tieBreaker & 0x00000000FF000000L) >> 24);
      tieBreakerBytes[5] = (byte)((tieBreaker & 0x0000000000FF0000L) >> 16);
      tieBreakerBytes[6] = (byte)((tieBreaker & 0x000000000000FF00L) >> 8);
      tieBreakerBytes[7] = (byte)((tieBreaker & 0x00000000000000FFL));

      IceControlledAttribute attribute = new IceControlledAttribute();
      attribute.setTieBreaker(tieBreakerBytes);

      return attribute;
    }

 //---------------------------------- PRIORITY ATTRIBUTE -----------------------

    /**
     * Creates a Priority attribute with the specified priority value
     *
     * @param priority  the priority value
     * @return  the created PriorityAttribute
     * @throws  StunException if priority < 0 or priority > (2^31 - 1)
     */
    public static PriorityAttribute createPriorityAttribute(long priority)
      throws StunException
    {
      PriorityAttribute attribute = new PriorityAttribute();

      attribute.setPriority(priority);

      return attribute;
    }

//--------------------------------- USE-CANDIDATE ATTRIBUTE ---------------------

    /**
     * Creates a UseCandidateAttribute
     *
     * @return  the created UseCandidateAttribute
     */
    public static UseCandidateAttribute createUseCandidateAttribute()
    {
      UseCandidateAttribute attribute = new UseCandidateAttribute();

      return attribute;
    }

    /**
     * Creates an IceControllingAttribute with the specified tie-breaker value
     *
     * @param tieBreaker  the tie-breaker value to be used
     *
     * @return  the created IceControllingAttribute
     * @throws  StunException of tie-breaker value is not valid
     */
    public static IceControllingAttribute createIceControllingAttribute(long tieBreaker)
    throws StunException
    {
      // generating the network ordered byte stream of the tieBreaker long variable
      byte[] tieBreakerBytes = new byte[8];
      tieBreakerBytes[0] = (byte)(tieBreaker>>56);
      tieBreakerBytes[1] = (byte)((tieBreaker & 0x00FF000000000000L) >> 48);
      tieBreakerBytes[2] = (byte)((tieBreaker & 0x0000FF0000000000L) >> 40);
      tieBreakerBytes[3] = (byte)((tieBreaker & 0x000000FF00000000L) >> 32);
      tieBreakerBytes[4] = (byte)((tieBreaker & 0x00000000FF000000L) >> 24);
      tieBreakerBytes[5] = (byte)((tieBreaker & 0x0000000000FF0000L) >> 16);
      tieBreakerBytes[6] = (byte)((tieBreaker & 0x000000000000FF00L) >> 8);
      tieBreakerBytes[7] = (byte)((tieBreaker & 0x00000000000000FFL));

      IceControllingAttribute attribute = new IceControllingAttribute();
      attribute.setTieBreaker(tieBreakerBytes);

      return attribute;
    }
}
