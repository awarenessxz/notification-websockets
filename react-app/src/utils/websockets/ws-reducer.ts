import { WebsocketState, WebsocketActionTypes, RECEIVED_TOAST_MESSAGE } from "./ws-types";

export const initialState: WebsocketState = {
    toastMsg: { title: "", message: "" },
};

export const WebsocketReducer = (state: WebsocketState = initialState, action: WebsocketActionTypes) => {
    switch (action.type) {
        case "RECEIVED_TOAST_MESSAGE":
            return {
                ...state,
                toastMsg: action.payload,
            }
        default:
            return state;
    }
};
