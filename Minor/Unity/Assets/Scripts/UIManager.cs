using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class UIManager : MonoBehaviour
{
    [SerializeField]
    private TracerManager tracerManager;
    [SerializeField]
    private List<GameObject> pause;
    [SerializeField]
    private GameObject controls;
    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        pause.ForEach(p => p.SetActive(tracerManager.paused));
        if (Input.GetKeyDown(KeyCode.Slash)) 
            controls.SetActive(!controls.activeInHierarchy);
    }
}
