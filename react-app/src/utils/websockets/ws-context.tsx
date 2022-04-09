import { Message } from "@stomp/stompjs";
import React, { Dispatch, useEffect } from "react";
import { ToastContainer, toast } from 'react-toastify';
import { WebsocketActionTypes, WebsocketState, WebsocketTopic } from "./ws-types";
import { WebsocketReducer, initialState } from "./ws-reducer";
import { dispatchNewTopicMessage } from "./ws-action";
import useStompWebSocket from "./UseStompWebsocket";
import 'react-toastify/dist/ReactToastify.css';

const WebsocketStateContext = React.createContext<WebsocketState | null>(null);
const WebsocketDispatchContext = React.createContext<Dispatch<WebsocketActionTypes> | null>(null);

export const useWebsocketState = (): WebsocketState => {
    const context = React.useContext(WebsocketStateContext);
    if (context === undefined) {
        throw new Error("useAuthState must be used within a AuthProvider");
    }
    if (context === null) {
        return { ...initialState };
    }
    return context;
};

export const useWebsocketDispatch = (): Dispatch<WebsocketActionTypes> | null => {
    const context = React.useContext(WebsocketDispatchContext);
    if (context === undefined) {
        throw new Error("useAuthDispatch must be used within a AuthProvider");
    }
    return context;
};

export const WebsocketProvider: React.FC = ({ children }) => {
    const [state, dispatch] = React.useReducer(WebsocketReducer, initialState);
    const { stompClient ,isWebSocketConnected } = useStompWebSocket({ socketPath: "/stomp" });

    useEffect(() => {
        if (isWebSocketConnected && stompClient !== null) {
            Object.values(WebsocketTopic).forEach((topic) => {
                stompClient.subscribe(topic, (message: Message) => {
                    dispatchNewTopicMessage(dispatch, topic, message);
                    toast("Notify me");
                });
            });
            stompClient.publish({ destination: "/server/test", body: "Testing 123!"});
        }
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [isWebSocketConnected, stompClient, dispatch]);

    return (
        <WebsocketStateContext.Provider value={state}>
            <WebsocketDispatchContext.Provider value={dispatch}>
                {children}
                <ToastContainer />
            </WebsocketDispatchContext.Provider>
        </WebsocketStateContext.Provider>
    );
};
