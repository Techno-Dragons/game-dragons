<script>
    import {onMount} from "svelte";
    import {goto} from "$app/navigation";
    import {toastNotice} from "../../../app.js";

    let userData2 = [];
    $: userData3 = {
        nickname: '',
        password1: '',
        password2: '',
    }
    onMount(async () => {
        let response = await fetch(`http://localhost:8090/member/mypage`, {
            credentials: 'include'
        }).then((res) => res.json());
        userData2 = response.data;
        userData3.nickname = response.data.nickname;
    });

    async function postModifiedData() {
        if(!blankCheck() && (userData2.nickname == userData3.nickname)){
            toastNotice("변경사항 입력해주세요.");
            return false;
        }
        userData3.password1 = userData3.password1.trim();
        userData3.password2 = userData3.password2.trim();
        if (passwordCheck()) {
            await fetch(`http://localhost:8090/member/mypage`, {
                headers: {
                    'Content-Type': 'application/json'
                },
                method: 'POST',
                body: JSON.stringify(userData3),
                credentials: 'include',
            })
            .then((res)=> res.json())
            .then((res)=>{
                if(res.resultCode=="200" && userData3.nickname != ''){
                    localStorage.setItem("nickname",userData3.nickname);
                }
                toastNotice(res.msg);
            })
            await goto('/');
        } else {
            toastNotice("비밀번호가 일치하지 않습니다.")
        }

    }

    function passwordCheck() {
        if (userData3.password1 == userData3.password2) {
            return true;
        }
        return false;
    }
    function blankCheck(){
        if (userData3.password1.trim() == '' || userData3.password2.trim() == '') {
            return false;
        }
        return true;
    }
</script>

<svelte:head>
    <title>Home</title>
    <meta name="description" content="Svelte demo app"/>
</svelte:head>

<section>
    <div class="card shadow-xl">
        <div class="card-body p-1">
            <h1 class="card-title justify-center">내 정보 변경</h1>
            <form class="p-8">
                <div class="card-body p-1">
                    <label class="card-title" for="username">사용자ID</label>
                    <input
                        type="text"
                        class="textarea textarea-bordered card-actions justify-end"
                        placeholder="{userData2.username}"
                        disabled="disabled"
                    />
                </div>
                <div class="card-body p-1">
                    <label class="card-title" for="nickname">닉네임</label>
                    <input
                        type="text"
                        class="textarea textarea-bordered"
                        placeholder="{userData2.nickname}"
                        bind:value={userData3.nickname}
                    />
                </div>
                <div class="card-body p-1">
                    <label class="card-title" for="password1">비밀번호</label>
                    <input
                        type="password"
                        class="textarea textarea-bordered"
                        placeholder="new password"
                        bind:value={userData3.password1}
                    />
                </div>
                <div class="card-body p-1">
                    <!-- // 비밀번호 확인하는 과정 추가 필요 -->
                    <label class="card-title" for="password2">비밀번호 확인
                        {#if passwordCheck() && blankCheck()}
                            <span id="passwordCheck">✅</span>
                        {/if}
                    </label>
                    <input
                        type="password"
                        class="textarea textarea-bordered"
                        placeholder="password confirm"
                        bind:value={userData3.password2}
                    />
                </div>
                <div class="card-body p-1">
                    <label class="card-title" for="email">이메일</label>
                    <input
                        type="email"
                        class="textarea textarea-bordered"
                        placeholder="{userData2.email}"
                        disabled="disabled"
                    />
                </div>
                <button class="btn" type="submit" on:click={(event)=>postModifiedData()}>변경사항 저장</button>
            </form>
        </div>
    </div>

</section>

<style>

</style>
