/* ***************************************************************************************
 * Type Definitions
 *************************************************************************************** */

export interface ToastMessage {
    title: string;
    message: string;
}

export enum WebsocketTopic {
    TOAST_MESSAGE = "/topic/toast_message"
}

/* ***************************************************************************************
 * Type Definition of State
 *************************************************************************************** */

export interface WebsocketState {
    toastMsg: ToastMessage;
}

/* ***************************************************************************************
 * List of all action type
 *************************************************************************************** */

export const RECEIVED_TOAST_MESSAGE = "RECEIVED_TOAST_MESSAGE";

/* ***************************************************************************************
 * Types Definition for all action type
 *************************************************************************************** */

interface ReceivedToastMessageAction {
    type: typeof RECEIVED_TOAST_MESSAGE;
    payload: ToastMessage;
}

// union action types
export type WebsocketActionTypes = ReceivedToastMessageAction;
