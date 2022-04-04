import { Dispatch } from "react";
import { Message } from "@stomp/stompjs";
import {WebsocketActionTypes, RECEIVED_TOAST_MESSAGE, WebsocketTopic} from "./ws-types";

export const dispatchNewTopicMessage = (dispatch: Dispatch<WebsocketActionTypes>, topic: string, message: Message) => {
    switch (topic) {
        case WebsocketTopic.TOAST_MESSAGE:
            dispatch({
                type: RECEIVED_TOAST_MESSAGE,
                payload: {
                    title: "Hello",
                    message: "World"
                }
            });
            break;
        default:
            console.error("Received unknown message...");
            break;
    }
};
