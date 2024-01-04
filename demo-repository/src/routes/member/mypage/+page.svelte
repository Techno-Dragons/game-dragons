<script>
    import {onMount} from "svelte";
    import {goto} from "$app/navigation";
    import {checkLogout} from "../login_check.js";

    checkLogout("로그인이 필요합니다.");

    let userData2 = [];
    onMount(async () => {
        let response = await fetch(`http://localhost:8090/member/mypage`,{
            credentials: 'include'
        }).then((res)=> res.json());
        userData2 = response.data;
    });


    $: userData3 = {
        nickname: '',
        password1: '',
        password2: '',
    }

    async function postModifiedData() {
        if (passwordCheck()) {
            await fetch(`http://localhost:8090/member/mypage`, {
                headers: {
                    'Content-Type': 'application/json'
                },
                method: 'POST',
                body: JSON.stringify(userData3)
            })
        } else {
            alert("비밀번호가 일치하지 않습니다.")
        }

    }

    function passwordCheck() {
        if (userData3.password1 == userData3.password2) {
            return true;
        }
        return false;
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
            <form class="p-5">
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
                        {#if passwordCheck()}
                            <span id="check">✅</span>
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
                <button class="btn" type="submit" on:click={postModifiedData()}>변경사항 저장</button>
            </form>
        </div>
    </div>

</section>

<style>

</style>
