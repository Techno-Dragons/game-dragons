import { goto } from "$app/navigation";
import {onMount} from "svelte";

/**
 * @param {string} msg
 */
export function checkLogout(msg){
    onMount(()=>{
        if(localStorage.getItem("username")==undefined){
            alert(msg);
            goto("/");
        }
    })
}

/**
 * @param {string} msg
 */
export function checkLogin(msg){
    onMount(()=>{
        if(localStorage.getItem("username")){
            alert(msg);
            goto("/");
        }
    })
}