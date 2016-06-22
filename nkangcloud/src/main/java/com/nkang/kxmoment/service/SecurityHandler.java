package com.nkang.kxmoment.service;

/**
 * Copyright 2010 Hewlett-Packard. All rights reserved. <br>
 * HP Confidential. Use is subject to license terms.
 */

import java.util.Set;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public final class SecurityHandler implements SOAPHandler<SOAPMessageContext> {

    private String username;
    private String password;

    public SecurityHandler(String username, String password) {
        this.username = username;
        this.password = password;
    }

    //@Override
    public boolean handleMessage(final SOAPMessageContext msgCtx) {

        // Indicator telling us which direction this message is going in
        final Boolean outInd = (Boolean) msgCtx.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

        // Handler must only add security headers to outbound messages
        if (outInd.booleanValue()) {
            try {
                // Get the SOAP Envelope
                final SOAPEnvelope envelope = msgCtx.getMessage().getSOAPPart().getEnvelope();

                // Header may or may not exist yet
                SOAPHeader header = envelope.getHeader();
                if (header == null) {
                    header = envelope.addHeader();
                }

                // Add WSS Usertoken Element Tree
                final SOAPElement security = header.addChildElement("Security", "wsse",
                        "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
                final SOAPElement userToken = security.addChildElement("UsernameToken", "wsse");
                userToken.addChildElement("Username", "wsse").addTextNode(username);
                userToken.addChildElement("Password", "wsse").addTextNode(password);

            } catch (final Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    //@Override
    public boolean handleFault(SOAPMessageContext context) {
        return false;
    }

    //@Override
    public void close(MessageContext context) {
    }

   // @Override
    public Set<QName> getHeaders() {
        return null;
    }

}