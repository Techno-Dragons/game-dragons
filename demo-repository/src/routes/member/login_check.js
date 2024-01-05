import { goto } from "$app/navigation";
import {onMount} from "svelte";

/**
 * @param {string} msg
 */
export function checkLogout(msg){
    onMount(()=>{
        if(localStorage.getItem("nickname")!=undefined){
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
        if(localStorage.getItem("nickname")){
            alert(msg);
            goto("/");
        }
    })
}

export async function logout(){
    await fetch(`http://localhost:8090/member/logout`,{
        headers: {
            'Content-Type': 'application/json'
        },
        method: 'POST',
        credentials: 'include'
    });
    localStorage.removeItem("username");
    localStorage.removeItem("nickname");
    await goto('/');
}