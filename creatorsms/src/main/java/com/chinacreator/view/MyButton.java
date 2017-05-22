package com.chinacreator.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * Created by Smile on 2017/5/16.
 */

public class MyButton extends Button {
    public static final String TAG = "事件分发";

    public MyButton(Context context) {
        super(context);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.i(TAG, "MyButton--dispatchTouchEvent--ACTION_DOWN");
                //这里的false相当于拦截 并且把事件向上传递
                break;
            case MotionEvent.ACTION_MOVE:

                Log.i(TAG, "MyButton--dispatchTouchEvent--ACTION_MOVE");
               break;
            case MotionEvent.ACTION_UP:
                Log.i(TAG, "MyButton--dispatchTouchEvent--ACTION_UP");
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.i(TAG, "MyButton--onTouchEvent--ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i(TAG, "MyButton--onTouchEvent--ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TAG, "MyButton--onTouchEvent--ACTION_UP");
                break;
        }
        return super.onTouchEvent(event);
    }


  /*  @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        boolean handled = false;
        if (onFilterTouchEventForSecurity(ev)) {
            final int action = ev.getAction();
            final int actionMasked = action & MotionEvent.ACTION_MASK;


            *//*
             这里判断是否为ACTION_DOWN动作  ACTION_DOWN只会最开始执行一次
             *//*
            if (actionMasked == MotionEvent.ACTION_DOWN) {

               *//* 清除和取消所有TouchTaget的对象 第一次进来 mFirstTouchTarget=null 所以这里面的方法不会执行
                mFirstTouchTarget的赋值在下面的addTouchTarget（）方法中对mFristTouchEvent赋值*//*
                cancelAndClearTouchTargets(ev);
                *//*检查mFirstTouchTarget是否为空  不为空 直接设置为null*//*
                resetTouchState();
            }

            // Check for interception.  是否拦截的标识
            final boolean intercepted;

            //这里只需要满足ACTION_DOWN或者mFristTouchTarget!=null(这个是针对ACTION_MOVE或者ACTION_UP动作需要执行的方法)
            if (actionMasked == MotionEvent.ACTION_DOWN
                    || mFirstTouchTarget != null) {
               *//*
               这里的mGroupFlags在上面的resetTouchState()赋值为：  mGroupFlags &= ~FLAG_DISALLOW_INTERCEPT;
                直接导致下面的运算为mGroupFlags & FLAG_DISALLOW_INTERCEPT=0 所以disallowIntercept=false
                这里只有一种情况在childview中执行requestDisallowInterceptTouchEvent(boolean disallowIntercept)
                if (disallowIntercept) {
                    mGroupFlags |= FLAG_DISALLOW_INTERCEPT;
                } else {
                    mGroupFlags &= ~FLAG_DISALLOW_INTERCEPT;
                }
                *//*
                final boolean disallowIntercept = (mGroupFlags & FLAG_DISALLOW_INTERCEPT) != 0;
                //只有在childview执行了requestDisallowInterceptTouchEvent（true）导致disallowIntercept为true
                if (!disallowIntercept) {
                    *//* 这里需要执行onInterceptTouchEvent() 获取到返回值
                  public boolean onInterceptTouchEvent(MotionEvent ev) {
                   if (ev.isFromSource(InputDevice.SOURCE_MOUSE)
                     && ev.getAction() == MotionEvent.ACTION_DOWN
                      && ev.isButtonPressed(MotionEvent.BUTTON_PRIMARY)
                       && isOnScrollbarThumb(ev.getX(), ev.getY())) {
                      return true;
                      }
                  return false;}
                  如果没有特殊情况的话 都是返回false
                  除非用户自己设置oninterceptTouchEvent的三种动作的返回值
                  *//*

                    intercepted = onInterceptTouchEvent(ev);
                    ev.setAction(action); // restore action in case it was changed
                } else {
                   *//*
                   这里执行了requestDisallowInterceptTouchEvent 所以这里是没有经过onInterceptTouchEvent
                   因此在onInterceptTouchEvent做的返回值是无效的 默认设置intrcepted为false  不拦截
                    *//*
                    intercepted = false;
                }
            } else {
              *//*
                不是ACTION_DOWN事件 正常情况下mFirstTouchTarget！=null 这个事件是不正常的
                *//*
                intercepted = true;
            }

            // If intercepted, start normal event dispatch. Also if there is already
            // a view that is handling the gesture, do normal event dispatch.
            //根Event设置mFirstTouchTarget
            if (intercepted || mFirstTouchTarget != null) {
                ev.setTargetAccessibilityFocus(false);
            }

            *//* private static boolean resetCancelNextUpFlag(@NonNull View view) {
              if ((view.mPrivateFlags & PFLAG_CANCEL_NEXT_UP_EVENT) != 0) {
                view.mPrivateFlags &= ~PFLAG_CANCEL_NEXT_UP_EVENT;
                   return true;
               }
             return false;
             }
             判断是否为ACTION_CANCEL事件
             *//*
            final boolean canceled = resetCancelNextUpFlag(this)
                    || actionMasked == MotionEvent.ACTION_CANCEL;

            // Update list of touch targets for pointer down, if needed.
            final boolean split = (mGroupFlags & FLAG_SPLIT_MOTION_EVENTS) != 0;
            TouchTarget newTouchTarget = null;
            boolean alreadyDispatchedToNewTouchTarget = false;
            *//*
            这里需要使用到上面的获取的intercepted 只要childview没有设置requestDisallowInterceptTouchEvent（true）
            或者只要不是取消事件
            *//*
            if (!canceled && !intercepted) {

                // If the event is targeting accessiiblity focus we give it to the
                // view that has accessibility focus and if it does not handle it
                // we clear the flag and dispatch the event to all children as usual.
                // We are looking up the accessibility focused host to avoid keeping
                // state since these events are very rare.
                View childWithAccessibilityFocus = ev.isTargetAccessibilityFocus()
                        ? findChildWithAccessibilityFocus() : null;

               *//*
               ACTION_DOWN事件可以进去
               ACTION_HOVER_MOVE是鼠标事件 这里不用考虑
               *//*
                if (actionMasked == MotionEvent.ACTION_DOWN
                        || (split && actionMasked == MotionEvent.ACTION_POINTER_DOWN)
                        || actionMasked == MotionEvent.ACTION_HOVER_MOVE) {
                    *//*
                    对于ACTION_DOWN事件而言为0
                    获取action的关联索引
                    *//*

                    final int actionIndex = ev.getActionIndex();
                    final int idBitsToAssign = split ? 1 << ev.getPointerId(actionIndex)
                            : TouchTarget.ALL_POINTER_IDS;

                    // Clean up earlier touch targets for this pointer id in case they
                    // have become out of sync.
                    removePointersFromTouchTargets(idBitsToAssign);

                    final int childrenCount = mChildrenCount;
                   *//* 这里需要获取到childrenCount 数量  newTouchTarget初始化为null 而且必须有孩子才能进入if中
                    如果没有孩子  按照view的执行事件 ontouchEnvent将会执行
                    *//*
                    if (newTouchTarget == null && childrenCount != 0) {
                        final float x = ev.getX(actionIndex);
                        final float y = ev.getY(actionIndex);
                        // Find a child that can receive the event.
                        // Scan children from front to back.
                        final ArrayList<View> preorderedList = buildTouchDispatchChildList();
                        final boolean customOrder = preorderedList == null
                                && isChildrenDrawingOrderEnabled();
                        final View[] children = mChildren;
                        *//*
                        对所有的孩子进行遍历
                        *//*
                        for (int i = childrenCount - 1; i >= 0; i--) {
                            final int childIndex = getAndVerifyPreorderedIndex(
                                    childrenCount, i, customOrder);
                            final View child = getAndVerifyPreorderedView(
                                    preorderedList, children, childIndex);

                            // If there is a view that has accessibility focus we want it
                            // to get the event first and if not handled we will perform a
                            // normal dispatch. We may do a double iteration but this is
                            // safer given the timeframe.
                            if (childWithAccessibilityFocus != null) {
                                if (childWithAccessibilityFocus != child) {
                                    continue;
                                }
                                childWithAccessibilityFocus = null;
                                i = childrenCount - 1;
                            }

                          *//*
                          这里判断child是VISIBILITY 或者是执行了动画 这样才可以接收事件
                           *//*
                            if (!canViewReceivePointerEvents(child)
                                    || !isTransformedTouchPointInView(x, y, child, null)) {
                                ev.setTargetAccessibilityFocus(false);
                                continue;
                            }

                           *//*
                           根据child获取到TouchTarget 这里是根据mFirstTouchTarget去对比
                           但是Down时候还没有赋值
                           *//*
                            newTouchTarget = getTouchTarget(child);
                            if (newTouchTarget != null) {
                                // Child is already receiving touch within its bounds.
                                // Give it the new pointer in addition to the ones it is handling.
                                newTouchTarget.pointerIdBits |= idBitsToAssign;
                                break;
                            }
                            *//*
                            重新设置NextUpFlag标签
                            *//*
                            resetCancelNextUpFlag(child);
                            //对于的child判断

                         *//*  if (child == null) {
                            //这里没有child 则viewgroup自己消费事件 对事件进行分发
                                handled = super.dispatchTouchEvent(transformedEvent);
                            } else {
                                final float offsetX = mScrollX - child.mLeft;
                                final float offsetY = mScrollY - child.mTop;
                                transformedEvent.offsetLocation(offsetX, offsetY);
                                if (! child.hasIdentityMatrix()) {
                                    transformedEvent.transform(child.getInverseMatrix());
                                }

                                handled = child.dispatchTouchEvent(transformedEvent);
                            }

                            上面是dispatchTransformedTouchEvent中代码
                            child不为null 通过MotionEvent把Viewgroup的值转换到child的坐标上 ，并且让child把事件传递下去
                            child.dispatchTouchEvent(transformedEvent);
                            child为null 直接把事件给该viewpager自己消费 将事件分发到OntouchEvent中去执行
                            这里对于dispatchTouchEvent的返回值可以去看看上篇对于View的事件分发
                            *//*

                            if (dispatchTransformedTouchEvent(ev, false, child, idBitsToAssign)) {
                                // Child wants to receive touch within its bounds.
                                mLastTouchDownTime = ev.getDownTime();
                                if (preorderedList != null) {
                                    // childIndex points into presorted list, find original index
                                    for (int j = 0; j < childrenCount; j++) {
                                        if (children[childIndex] == mChildren[j]) {
                                            mLastTouchDownIndex = j;
                                            break;
                                        }
                                    }
                                } else {
                                    mLastTouchDownIndex = childIndex;
                                }
                                mLastTouchDownX = ev.getX();
                                mLastTouchDownY = ev.getY();
                                //这里将mFirstTouchTarget设置为TouchTarget
                                newTouchTarget = addTouchTarget(child, idBitsToAssign);
                                alreadyDispatchedToNewTouchTarget = true;
                                *//*直接跳出for循环 交给child去执行事件分发 分为viewgroup和view去分发事件*//*
                                break;
                            }

                            // The accessibility focus didn't handle the event, so clear
                            // the flag and do a normal dispatch to all children.
                            ev.setTargetAccessibilityFocus(false);
                        }
                        if (preorderedList != null) preorderedList.clear();
                    }

                    if (newTouchTarget == null && mFirstTouchTarget != null) {
                        // Did not find a child to receive the event.
                        // Assign the pointer to the least recently added target.
                        newTouchTarget = mFirstTouchTarget;
                        while (newTouchTarget.next != null) {
                            newTouchTarget = newTouchTarget.next;
                        }
                        newTouchTarget.pointerIdBits |= idBitsToAssign;
                    }
                }
            }

            // Dispatch to touch targets.
            if (mFirstTouchTarget == null) {
                // No touch targets so treat this as an ordinary view.
                handled = dispatchTransformedTouchEvent(ev, canceled, null,
                        TouchTarget.ALL_POINTER_IDS);
            } else {
                // Dispatch to touch targets, excluding the new touch target if we already
                // dispatched to it.  Cancel touch targets if necessary.
                TouchTarget predecessor = null;
                TouchTarget target = mFirstTouchTarget;
                while (target != null) {
                    final TouchTarget next = target.next;
                    if (alreadyDispatchedToNewTouchTarget && target == newTouchTarget) {
                        handled = true;
                    } else {
                        final boolean cancelChild = resetCancelNextUpFlag(target.child)
                                || intercepted;
                        if (dispatchTransformedTouchEvent(ev, cancelChild,
                                target.child, target.pointerIdBits)) {
                            handled = true;
                        }
                        if (cancelChild) {
                            if (predecessor == null) {
                                mFirstTouchTarget = next;
                            } else {
                                predecessor.next = next;
                            }
                            target.recycle();
                            target = next;
                            continue;
                        }
                    }
                    predecessor = target;
                    target = next;
                }
            }

            // Update list of touch targets for pointer up or cancel, if needed.
            if (canceled
                    || actionMasked == MotionEvent.ACTION_UP
                    || actionMasked == MotionEvent.ACTION_HOVER_MOVE) {
               *//*
               取消操作 直接将mFirstTouchTarget设置为空
                *//*
                resetTouchState();
            } else if (split && actionMasked == MotionEvent.ACTION_POINTER_UP) {
                final int actionIndex = ev.getActionIndex();
                final int idBitsToRemove = 1 << ev.getPointerId(actionIndex);
                removePointersFromTouchTargets(idBitsToRemove);
            }
        }

        return handled;
    }
        *//*
        * 总结
        * 上面我们可以分为ACTION_DOWN：
        * 第一次进来首先判断是否为ACTION_DOWN 如果是ACTION_DOWN直接将mFirstTouchTarget设置为空
        * 第一次获取的intercepted一定为false 进入到onInterceptTouchEvent（）方法中获取对于的返回值 默认是不拦截
        * 因为进入遍历child进行事件分发只有2中情况 一种是intercepted=false或者canceled 取消事件
        *
        * ACTION_MOVE:
        * 当执行完ACTION_DOWN事件 mFirstTouchTarget已经不为空 这是同样可以进入到onInterceptTouchEvent（），
        * 在这里处理是否拦截ACTION_DOWN事件，之后按照上面的child进行事件的分发
        *
        * ACTION_UP ：
        * 基本和ACTION_MOVE一样的流程
        *
        * ACTION_CANCEL:
        * 在ACTION_UP之后执行   会将mFirstTouchTarget设置为空
        *
        *
        * 其实上面已经重点地方已经解释的很清楚了 说几个重要的地方：
        *  final boolean disallowIntercept = (mGroupFlags & FLAG_DISALLOW_INTERCEPT) != 0;
        *
         这里的mGroupFlags在上面的resetTouchState()赋值为：  mGroupFlags &= ~FLAG_DISALLOW_INTERCEPT;
         直接导致下面的运算为mGroupFlags & FLAG_DISALLOW_INTERCEPT=0 所以disallowIntercept=false
         如果为false
         之后的数据就是根据onInterceptTouchEvent() 方法的返回值了 这里就是拦截事件生效的地方

         如果我们不想拦截子类的事件 执行requestDisallowInterceptTouchEvent(true)，执行之后会跳过onInterceptTouchEvent() 这就是为什么拦截无效
         这里只有一种情况在childview中执行requestDisallowInterceptTouchEvent(boolean disallowIntercept)
         if (disallowIntercept) {
         mGroupFlags |= FLAG_DISALLOW_INTERCEPT;
         } else {
         mGroupFlags &= ~FLAG_DISALLOW_INTERCEPT;
          }
         注意这个方法必须在ACTION_DOWN事件生效之后才能执行 因为只有在child的OntouchEvent()执行才能有用
         如果直接在viewgroup就把事件屏蔽了 requestDisallowInterceptTouchEvent(boolean disallowIntercept)
         无法执行 导致后面事件无法执行的
         *//*

    *//**
     * Resets all touch state in preparation for a new cycle.
     *//*
    private void resetTouchState() {
        clearTouchTargets();
        resetCancelNextUpFlag(this);
        mGroupFlags &= ~FLAG_DISALLOW_INTERCEPT;
        mNestedScrollAxes = SCROLL_AXIS_NONE;
    }*/


}
