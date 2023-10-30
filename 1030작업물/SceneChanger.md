## SceneChanger.cs

```
using UnityEngine;
using UnityEngine.SceneManagement;

public class SceneChanger : MonoBehaviour
{
    public void Goto(string target) {
        SceneManager.LoadScene(target);
    }

    public void ScreenDirChange(bool isCol) {
        if (isCol) {
            Screen.orientation = ScreenOrientation.Portrait;
        } else {
            Screen.orientation = ScreenOrientation.LandscapeLeft;
        }

    }

    // public GameObject mainSceneRoot; // 메인 씬의 모든 게임 오브젝트를 포함하는 부모 오브젝트

    public void LoadSceneAdditively(string sceneName)
    {
        // 메인 씬의 게임 오브젝트를 비활성화
        GameManager.Instance.mainSceneRoot.SetActive(false);

        // 씬을 추가로 로드
        SceneManager.LoadScene(sceneName, LoadSceneMode.Additive);
    }

    public void UnloadScene(string sceneName)
    {
        Scene sceneToUnload = SceneManager.GetSceneByName(sceneName);

        if (sceneToUnload.isLoaded)
        {
            // 씬 언로드
            SceneManager.UnloadSceneAsync(sceneName);

            // 만약 현재 로드된 씬이 메인 씬만 있다면 메인 씬의 게임 오브젝트를 다시 활성화
            if (SceneManager.sceneCount == 2)
            {
                GameManager.Instance.mainSceneRoot.SetActive(true);
            }
        }
        else
        {
            Debug.LogWarning(sceneName + " is not loaded.");
        }
    }
}

```
